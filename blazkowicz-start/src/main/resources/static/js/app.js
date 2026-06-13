const DEFAULT_SCRIPT = 'class Main {\n' +
    '    static boolean run(String userId, String param) {\n' +
    '        if (userId == "114515") {\n' +
    '            return param == "test"\n' +
    '        } else {\n' +
    '            return false;\n' +
    '        }\n' +
    '    }\n' +
    '}';

const state = {
    enums: {},
    rules: [],
    strategies: [],
    events: [],
    currentEvent: null,
    editingRuleId: null,
    editingStrategy: false
};

async function api(path, options = {}) {
    const res = await fetch(path, {
        headers: { 'Content-Type': 'application/json', ...(options.headers || {}) },
        ...options
    });
    return res.json();
}

function toast(msg, type = 'success') {
    const el = document.getElementById('toast');
    el.textContent = msg;
    el.className = 'toast ' + type;
    el.classList.remove('hidden');
    setTimeout(() => el.classList.add('hidden'), 3000);
}

function switchPage(page) {
    document.querySelectorAll('.nav-item').forEach(btn => {
        btn.classList.toggle('active', btn.dataset.page === page);
    });
    document.querySelectorAll('.page').forEach(sec => {
        sec.classList.toggle('active', sec.id === 'page-' + page);
    });
    const titles = { dashboard: '概览', rules: '规则配置', strategies: '策略配置', events: '事件管理', runtime: '运行态测试' };
    document.getElementById('page-title').textContent = titles[page] || page;
    if (page === 'rules') loadRules();
    if (page === 'strategies') loadStrategies();
    if (page === 'events') loadEvents();
    if (page === 'runtime') loadRuntimeStrategies();
    if (page === 'dashboard') loadDashboard();
}

document.querySelectorAll('.nav-item').forEach(btn => {
    btn.addEventListener('click', () => switchPage(btn.dataset.page));
});

function fillSelect(id, values, selected) {
    const sel = document.getElementById(id);
    if (!sel || !values) return;
    sel.innerHTML = values.map(v => `<option value="${v}" ${v === selected ? 'selected' : ''}>${v}</option>`).join('');
}

async function loadEnums() {
    const res = await api('/config/meta/enums');
    if (!res.success) return;
    state.enums = res.data;
    fillSelect('strategy-bi', res.data.businessIdentity, 'TEST');
    fillSelect('strategy-pt', res.data.preventionType, 'TEST');
    fillSelect('runtime-bi', res.data.businessIdentity, 'TEST');
    fillSelect('runtime-pt', res.data.preventionType, 'TEST');
    fillSelect('strategy-disposal-type', res.data.disposalType, 'AUDIT');
    fillSelect('event-access-method', res.data.accessMethod, 'HTTP');
    fillSelect('new-event-access-method', res.data.accessMethod, 'HTTP');
    fillSelect('feature-type', res.data.featureType, 'ATTRIBUTE');
    fillSelect('pack-bi', res.data.businessIdentity, 'TEST');
    fillSelect('pack-pt', res.data.preventionType, 'TEST');
}

async function checkHealth() {
    const dot = document.getElementById('health-dot');
    const text = document.getElementById('health-text');
    try {
        const res = await api('/config/rules');
        if (res.success) {
            dot.className = 'status-dot ok';
            text.textContent = '服务正常';
        } else {
            dot.className = 'status-dot err';
            text.textContent = '服务异常';
        }
    } catch (e) {
        dot.className = 'status-dot err';
        text.textContent = '无法连接';
    }
}

async function loadDashboard() {
    const bi = document.getElementById('runtime-bi')?.value || 'TEST';
    const pt = document.getElementById('runtime-pt')?.value || 'TEST';
    const [rulesRes, strategiesRes, eventsRes, runtimeRes] = await Promise.all([
        api('/config/rules'),
        api(`/config/strategies?business_identity=${bi}&prevention_type=${pt}`),
        api('/config/events'),
        api(`/runtime/strategies?business_identity=${bi}&prevention_type=${pt}`)
    ]);
    document.getElementById('stat-rules').textContent =
        rulesRes.success ? (rulesRes.data || []).length : '-';
    document.getElementById('stat-strategies').textContent =
        strategiesRes.success ? (strategiesRes.data || []).length : '-';
    document.getElementById('stat-events').textContent =
        eventsRes.success ? (eventsRes.data || []).length : '-';
    document.getElementById('stat-runtime').textContent =
        runtimeRes.success ? (runtimeRes.data || []).length : '-';
}

async function invalidateCache() {
    const bi = document.getElementById('runtime-bi')?.value || 'TEST';
    const pt = document.getElementById('runtime-pt')?.value || 'TEST';
    const res = await api(`/config/cache/invalidate?business_identity=${bi}&prevention_type=${pt}`, { method: 'POST' });
    toast(res.success ? '缓存已刷新' : (res.msg || '刷新失败'), res.success ? 'success' : 'error');
}

async function loadRules() {
    const res = await api('/config/rules');
    if (!res.success) { toast(res.msg || '加载规则失败', 'error'); return; }
    state.rules = res.data || [];
    const tbody = document.getElementById('rules-table-body');
    tbody.innerHTML = state.rules.map(r => `
        <tr>
            <td>${r.ruleId}</td>
            <td>${escapeHtml(r.ruleName)}</td>
            <td><code>${escapeHtml(r.ruleLogic)}</code></td>
            <td>${escapeHtml(r.leftParamType || '-')}</td>
            <td>${r.version ?? 0}</td>
            <td><button class="btn sm" onclick="editRule(${r.ruleId})">编辑</button></td>
        </tr>
    `).join('') || '<tr><td colspan="6" style="color:var(--muted)">暂无规则</td></tr>';
    refreshRuleSelect();
}

function refreshRuleSelect() {
    const sel = document.getElementById('strategy-rule-id');
    if (!sel) return;
    sel.innerHTML = state.rules.map(r =>
        `<option value="${r.ruleId}">${r.ruleId} - ${escapeHtml(r.ruleName)}</option>`
    ).join('');
}

function buildConditions(script) {
    const conditions = [1, 2, 3].map(id => ({
        conditionId: id,
        conditionScript: { content: script, type: 'GROOVY' },
        requiredValues: [{ type: 'CONSTANT', name: 'param', value: 'test' }],
        leftParamType: 'ACCOUNT'
    }));
    return JSON.stringify(conditions, null, 2);
}

function openRuleForm() {
    state.editingRuleId = null;
    document.getElementById('rule-form-title').textContent = '新建规则';
    document.getElementById('rule-edit-id').value = '';
    document.getElementById('rule-name').value = '';
    document.getElementById('rule-logic').value = '1&&2&&3';
    document.getElementById('rule-script').value = DEFAULT_SCRIPT;
    document.getElementById('rule-conditions').value = buildConditions(DEFAULT_SCRIPT);
    document.getElementById('rule-form-card').classList.remove('hidden');
}

function closeRuleForm() {
    document.getElementById('rule-form-card').classList.add('hidden');
}

function generateConditions() {
    const script = document.getElementById('rule-script').value;
    document.getElementById('rule-conditions').value = buildConditions(script);
}

async function editRule(ruleId) {
    const res = await api(`/config/rules/${ruleId}`);
    if (!res.success) { toast(res.msg || '加载失败', 'error'); return; }
    const r = res.data;
    state.editingRuleId = ruleId;
    document.getElementById('rule-form-title').textContent = '编辑规则 #' + ruleId;
    document.getElementById('rule-edit-id').value = ruleId;
    document.getElementById('rule-name').value = r.ruleName;
    document.getElementById('rule-logic').value = r.ruleLogic;
    document.getElementById('rule-conditions').value = r.ruleConditions;
    try {
        const conditions = JSON.parse(r.ruleConditions);
        document.getElementById('rule-script').value =
            conditions[0]?.conditionScript?.content || DEFAULT_SCRIPT;
    } catch (e) {
        document.getElementById('rule-script').value = DEFAULT_SCRIPT;
    }
    document.getElementById('rule-form-card').classList.remove('hidden');
}

async function saveRule() {
    const name = document.getElementById('rule-name').value.trim();
    const logic = document.getElementById('rule-logic').value.trim();
    const conditions = document.getElementById('rule-conditions').value.trim();
    if (!name || !logic || !conditions) { toast('请填写完整', 'error'); return; }
    try { JSON.parse(conditions); } catch (e) { toast('条件 JSON 格式错误', 'error'); return; }

    let res;
    if (state.editingRuleId) {
        res = await api(`/config/rules/${state.editingRuleId}`, {
            method: 'PUT',
            body: JSON.stringify({ ruleConditions: conditions, ruleLogic: logic })
        });
    } else {
        res = await api('/config/rules', {
            method: 'POST',
            body: JSON.stringify({ ruleName: name, ruleConditions: conditions, ruleLogic: logic })
        });
    }
    toast(res.success ? '保存成功' : (res.msg || '保存失败'), res.success ? 'success' : 'error');
    if (res.success) { closeRuleForm(); loadRules(); loadDashboard(); }
}

async function loadStrategies() {
    const bi = document.getElementById('strategy-bi').value;
    const pt = document.getElementById('strategy-pt').value;
    const res = await api(`/config/strategies?business_identity=${bi}&prevention_type=${pt}`);
    if (!res.success) { toast(res.msg || '加载策略失败', 'error'); return; }
    state.strategies = res.data || [];
    const tbody = document.getElementById('strategies-table-body');
    tbody.innerHTML = state.strategies.map(s => `
        <tr>
            <td>${escapeHtml(s.name)}</td>
            <td>${escapeHtml(s.ruleName || s.introducedRuleId)}</td>
            <td>${escapeHtml(s.disposalType || '-')}</td>
            <td>${escapeHtml(s.disposalAction || '-')}</td>
            <td><button class="btn sm" onclick="editStrategy('${escapeAttr(s.name)}')">编辑</button></td>
        </tr>
    `).join('') || '<tr><td colspan="5" style="color:var(--muted)">暂无策略</td></tr>';
}

function openStrategyForm() {
    state.editingStrategy = false;
    document.getElementById('strategy-form-title').textContent = '新建策略';
    document.getElementById('strategy-name').value = '';
    document.getElementById('strategy-name').disabled = false;
    document.getElementById('strategy-desc').value = '';
    document.getElementById('strategy-action').value = 'MANUAL_REVIEW';
    document.getElementById('strategy-message').value = '风险命中，需人工审核';
    document.getElementById('strategy-form-card').classList.remove('hidden');
    loadRules();
}

function closeStrategyForm() {
    document.getElementById('strategy-form-card').classList.add('hidden');
}

async function editStrategy(name) {
    const bi = document.getElementById('strategy-bi').value;
    const pt = document.getElementById('strategy-pt').value;
    const res = await api(`/config/strategies/detail?business_identity=${bi}&prevention_type=${pt}&name=${encodeURIComponent(name)}`);
    if (!res.success) { toast(res.msg || '加载失败', 'error'); return; }
    const s = res.data;
    state.editingStrategy = true;
    document.getElementById('strategy-form-title').textContent = '编辑策略';
    document.getElementById('strategy-name').value = s.name;
    document.getElementById('strategy-name').disabled = true;
    document.getElementById('strategy-desc').value = s.description || '';
    document.getElementById('strategy-rule-id').value = s.introducedRuleId;
    document.getElementById('strategy-disposal-type').value = s.disposalType || 'AUDIT';
    document.getElementById('strategy-action').value = s.disposalAction || 'MANUAL_REVIEW';
    document.getElementById('strategy-message').value = s.disposalMessage || '';
    document.getElementById('strategy-form-card').classList.remove('hidden');
    loadRules();
}

async function saveStrategy() {
    const bi = document.getElementById('strategy-bi').value;
    const pt = document.getElementById('strategy-pt').value;
    const payload = {
        businessIdentity: bi,
        preventionType: pt,
        name: document.getElementById('strategy-name').value.trim(),
        description: document.getElementById('strategy-desc').value.trim(),
        introducedRuleId: Number(document.getElementById('strategy-rule-id').value),
        disposalType: document.getElementById('strategy-disposal-type').value,
        disposalAction: document.getElementById('strategy-action').value.trim(),
        disposalMessage: document.getElementById('strategy-message').value.trim()
    };
    if (!payload.name || !payload.introducedRuleId) { toast('请填写完整', 'error'); return; }

    const res = await api('/config/strategies', {
        method: state.editingStrategy ? 'PUT' : 'POST',
        body: JSON.stringify(payload)
    });
    toast(res.success ? '保存成功' : (res.msg || '保存失败'), res.success ? 'success' : 'error');
    if (res.success) { closeStrategyForm(); loadStrategies(); loadDashboard(); }
}

async function loadRuntimeStrategies() {
    const bi = document.getElementById('runtime-bi').value;
    const pt = document.getElementById('runtime-pt').value;
    const res = await api(`/runtime/strategies?business_identity=${bi}&prevention_type=${pt}`);
    const container = document.getElementById('runtime-strategies');
    if (!res.success) {
        container.textContent = res.msg || '加载失败';
        return;
    }
    const list = res.data || [];
    container.innerHTML = list.length ? list.map(s => `
        <div class="runtime-item">
            <strong>${escapeHtml(s.ruleName)}</strong> (ID: ${s.ruleId})<br>
            逻辑: <code>${escapeHtml(s.ruleLogic)}</code><br>
            处置: ${escapeHtml(s.disposalType || '-')} / ${escapeHtml(s.disposalAction || '-')}
        </div>
    `).join('') : '<span style="color:var(--muted)">当前场景无生效策略</span>';
}

async function runPrevention() {
    const payload = {
        businessIdentity: document.getElementById('runtime-bi').value,
        preventionType: document.getElementById('runtime-pt').value,
        userId: document.getElementById('runtime-user-id').value.trim()
    };
    const res = await api('/prevention/request', { method: 'POST', body: JSON.stringify(payload) });
    const pre = document.getElementById('runtime-result');
    if (!res.success) {
        pre.textContent = JSON.stringify(res, null, 2);
        toast(res.msg || '执行失败', 'error');
        return;
    }
    const results = res.data?.identityResultRespList || [];
    let output = JSON.stringify(res, null, 2);
    if (results.length) {
        output += '\n\n--- 结果摘要 ---\n';
        results.forEach(r => {
            output += `\n规则: ${r.name}  状态: ${r.status}`;
            if (r.disposalType) output += `  处置: ${r.disposalType}/${r.disposalAction}`;
        });
    }
    pre.textContent = output;
    toast('测试完成', 'success');
}

function escapeHtml(str) {
    if (str == null) return '';
    return String(str).replace(/&/g,'&amp;').replace(/</g,'&lt;').replace(/>/g,'&gt;').replace(/"/g,'&quot;');
}

function escapeAttr(str) {
    return escapeHtml(str).replace(/'/g, '&#39;');
}

document.getElementById('strategy-bi')?.addEventListener('change', loadStrategies);
document.getElementById('strategy-pt')?.addEventListener('change', loadStrategies);

async function loadEvents() {
    const res = await api('/config/events');
    if (!res.success) { toast(res.msg || '加载事件失败', 'error'); return; }
    state.events = res.data || [];
    const tbody = document.getElementById('events-table-body');
    tbody.innerHTML = state.events.map(e => `
        <tr>
            <td>${e.eventId}</td>
            <td>${escapeHtml(e.name)}</td>
            <td><code>${escapeHtml(e.code)}</code></td>
            <td>${escapeHtml(e.accessMethod)}</td>
            <td>${(e.features || []).length}</td>
            <td><button class="btn sm" onclick="openEventDetail(${e.eventId})">管理</button></td>
        </tr>
    `).join('') || '<tr><td colspan="6" style="color:var(--muted)">暂无事件</td></tr>';
}

function openEventForm() {
    document.getElementById('event-form-card').classList.remove('hidden');
    document.getElementById('new-event-name').value = '';
    document.getElementById('new-event-code').value = '';
    document.getElementById('new-event-operator').value = 'admin';
    document.getElementById('new-event-desc').value = '';
}

function closeEventForm() {
    document.getElementById('event-form-card').classList.add('hidden');
}

async function createEvent() {
    const payload = {
        name: document.getElementById('new-event-name').value.trim(),
        code: document.getElementById('new-event-code').value.trim(),
        lastOperator: document.getElementById('new-event-operator').value.trim(),
        description: document.getElementById('new-event-desc').value.trim(),
        accessMethod: document.getElementById('new-event-access-method').value
    };
    if (!payload.name || !payload.code) { toast('请填写名称和编码', 'error'); return; }
    const res = await api('/config/events', { method: 'POST', body: JSON.stringify(payload) });
    toast(res.success ? '创建成功' : (res.msg || '创建失败'), res.success ? 'success' : 'error');
    if (res.success) {
        closeEventForm();
        await loadEvents();
        loadDashboard();
    }
}

async function openEventDetail(eventId) {
    const res = await api(`/config/events/${eventId}`);
    if (!res.success) { toast(res.msg || '加载失败', 'error'); return; }
    state.currentEvent = res.data;
    const e = res.data;
    document.getElementById('event-detail-card').classList.remove('hidden');
    document.getElementById('event-detail-title').textContent = `事件：${e.name}`;
    document.getElementById('event-detail-id').value = e.eventId;
    document.getElementById('event-name').value = e.name || '';
    document.getElementById('event-code').value = e.code || '';
    document.getElementById('event-operator').value = e.lastOperator || '';
    document.getElementById('event-desc').value = e.description || '';
    document.getElementById('event-access-method').value = e.accessMethod || 'HTTP';
    renderEventFeatures(e.features || []);
    const pack = e.strategyPack || {};
    document.getElementById('pack-name').value = pack.name || '';
    document.getElementById('pack-bi').value = pack.businessIdentity || 'TEST';
    document.getElementById('pack-pt').value = pack.preventionType || 'TEST';
    document.getElementById('pack-operator').value = pack.lastOperator || '';
    document.getElementById('pack-desc').value = pack.description || '';
    document.getElementById('pack-strategy-names').value = (pack.strategyNames || []).join(',');
}

function renderEventFeatures(features) {
    const container = document.getElementById('event-features');
    container.innerHTML = features.length ? features.map(f => `
        <div class="runtime-item">
            <strong>${escapeHtml(f.name)}</strong> [${escapeHtml(f.type)}]<br>
            ${escapeHtml(f.description || '-')}
        </div>
    `).join('') : '<span style="color:var(--muted)">暂无特征</span>';
}

async function saveEventInfo() {
    const eventId = document.getElementById('event-detail-id').value;
    const payload = {
        name: document.getElementById('event-name').value.trim(),
        lastOperator: document.getElementById('event-operator').value.trim(),
        description: document.getElementById('event-desc').value.trim(),
        accessMethod: document.getElementById('event-access-method').value
    };
    const res = await api(`/config/events/${eventId}`, { method: 'PUT', body: JSON.stringify(payload) });
    toast(res.success ? '保存成功' : (res.msg || '保存失败'), res.success ? 'success' : 'error');
    if (res.success) { loadEvents(); openEventDetail(eventId); loadDashboard(); }
}

async function addEventFeature() {
    const eventId = document.getElementById('event-detail-id').value;
    const payload = {
        type: document.getElementById('feature-type').value,
        name: document.getElementById('feature-name').value.trim(),
        description: document.getElementById('feature-desc').value.trim()
    };
    if (!payload.name) { toast('请填写特征名称', 'error'); return; }
    const res = await api(`/config/events/${eventId}/features`, { method: 'POST', body: JSON.stringify(payload) });
    toast(res.success ? '特征已添加' : (res.msg || '添加失败'), res.success ? 'success' : 'error');
    if (res.success) {
        document.getElementById('feature-name').value = '';
        document.getElementById('feature-desc').value = '';
        openEventDetail(eventId);
    }
}

async function saveEventStrategyPack() {
    const eventId = document.getElementById('event-detail-id').value;
    const namesRaw = document.getElementById('pack-strategy-names').value.trim();
    const payload = {
        lastOperator: document.getElementById('pack-operator').value.trim(),
        description: document.getElementById('pack-desc').value.trim(),
        businessIdentity: document.getElementById('pack-bi').value,
        preventionType: document.getElementById('pack-pt').value,
        name: document.getElementById('pack-name').value.trim(),
        strategyNames: namesRaw ? namesRaw.split(',').map(s => s.trim()).filter(Boolean) : []
    };
    if (!payload.name) { toast('请填写策略包名称', 'error'); return; }
    const res = await api(`/config/events/${eventId}/strategy-pack`, { method: 'PUT', body: JSON.stringify(payload) });
    toast(res.success ? '策略包已保存' : (res.msg || '保存失败'), res.success ? 'success' : 'error');
    if (res.success) openEventDetail(eventId);
}

(async function init() {
    await loadEnums();
    await checkHealth();
    await loadDashboard();
})();
