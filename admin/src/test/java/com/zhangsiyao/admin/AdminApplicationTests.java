package com.zhangsiyao.admin;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.zhangsiyao.admin.service.CompileParamService;
import com.zhangsiyao.admin.service.CompilerService;
import com.zhangsiyao.admin.service.ExampleService;
import com.zhangsiyao.admin.service.ProblemService;
import com.zhangsiyao.common.compiler.CppCompiler;
import com.zhangsiyao.common.result.JudgeResult;
import com.zhangsiyao.common.send.JudgeParam;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@SpringBootTest
class AdminApplicationTests {


    @Autowired
    ProblemService problemService;

    @Autowired
    CompilerService compilerService;

    @Autowired
    CompileParamService compileParamService;

    @Autowired
    ExampleService exampleService;


    @Test
    void a() throws JsonProcessingException {
        CppCompiler compiler=new CppCompiler("http://222.187.223.125:35811",20);
        try {
            compiler.compile("#pragma GCC optimize(\"O2\")\n" +
                    "#pragma GCC optimize(\"O3\")\n" +
                    "#pragma GCC optimize(\"Ofast\")\n" +
                    "#pragma GCC optimize(\"unroll-loops\")\n" +
                    "#pragma GCC target(\"avx,avx2,fma\")\n" +
                    " \n" +
                    "#include <bits/stdc++.h>\n" +
                    " \n" +
                    "using namespace std;\n" +
                    " \n" +
                    "#define out(x) cout << #x << '=' << (x) << endl\n" +
                    "#define out2(x, y) cout << #x << '=' << (x) << ',' << #y << '=' << (y) << endl \n" +
                    "#define no do { cout << \"No\" << endl; return; } while(0)\n" +
                    "#define yes do { cout << \"Yes\" << endl; return; } while (0)\n" +
                    "#define outvec(a) do { for (auto &v : (a)) { cout << v << ' '; } cout << endl; } while (0)\n" +
                    "#define lowbit(x) ((x) & -(x))\n" +
                    "#define gcd __gcd \n" +
                    "#define inf 0x3f3f3f3f3f3f3f3fLL\n" +
                    "#define infi 0x3f3f3f3f\n" +
                    " \n" +
                    "using ll = long long;\n" +
                    "using pii = pair<int, int>;\n" +
                    " \n" +
                    "template<typename T> ostream & operator << (ostream &out,const set<T>&obj){out<<\"set(\";for(auto it=obj.begin();it!=obj.end();it++) out<<(it==obj.begin()?\"\":\", \")<<*it;out<<\")\";return out;}\n" +
                    "template<typename T1,typename T2> ostream & operator << (ostream &out,const map<T1,T2>&obj){out<<\"map(\";for(auto it=obj.begin();it!=obj.end();it++) out<<(it==obj.begin()?\"\":\", \")<<it->first<<\": \"<<it->second;out<<\")\";return out;}\n" +
                    "template<typename T1,typename T2> ostream & operator << (ostream &out,const pair<T1,T2>&obj){out<<\"<\"<<obj.first<<\", \"<<obj.second<<\">\";return out;}\n" +
                    "template<typename T> ostream & operator << (ostream &out,const vector<T>&obj){out<<\"vector(\";for(auto it=obj.begin();it!=obj.end();it++) out<<(it==obj.begin()?\"\":\", \")<<*it;out<<\")\";return out;}\n" +
                    "\n" +
                    "void solve() {\n" +
                    "\tint n, d;\n" +
                    "\tcin >> n >> d;\n" +
                    "\tvector<array<int, 2>> p(n + 1);\n" +
                    "\tfor (int i = 1; i <= n; i++) {\n" +
                    "\t\tcin >> p[i][0] >> p[i][1];\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\tsort(p.begin() + 1, p.end());\n" +
                    "\tvector<vector<array<int, 2>>> segleft(n * 4 + 4), segright(n * 4 + 4);\n" +
                    "\tfunction<void(int, int, int)> build = [&](int x, int l, int r) -> void {\n" +
                    "\t\tif (l == r) {\n" +
                    "\t\t\tsegleft[x].push_back({p[l][1] - p[l][0], l});\n" +
                    "\t\t\tsegright[x].push_back({p[l][1] + p[l][0], l});\n" +
                    "\t\t} else {\n" +
                    "\t\t\tint mid = l + (r - l) / 2;\n" +
                    "\t\t\tbuild(x * 2, l, mid);\n" +
                    "\t\t\tbuild(x * 2 + 1, mid + 1, r);\n" +
                    "\t\t\tauto tr = [&](vector<array<int, 2>> &f, vector<array<int, 2>> &lc, vector<array<int, 2>> &rc) -> void {\n" +
                    "\t\t\t\tint i = 0, j = 0;\n" +
                    "\t\t\t\tf.reserve(lc.size() + rc.size());\n" +
                    "\t\t\t\twhile (i < lc.size() && j < rc.size()) {\n" +
                    "\t\t\t\t\tif (lc[i] >= rc[j]) {\n" +
                    "\t\t\t\t\t\tf.push_back(lc[i++]);\n" +
                    "\t\t\t\t\t} else {\n" +
                    "\t\t\t\t\t\tf.push_back(rc[j++]);\n" +
                    "\t\t\t\t\t}\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t\twhile (i < lc.size()) {\n" +
                    "\t\t\t\t\tf.push_back(lc[i++]);\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t\twhile (j < rc.size()) {\n" +
                    "\t\t\t\t\tf.push_back(rc[j++]);\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\ttr(segleft[x], segleft[x * 2], segleft[x * 2 + 1]);\n" +
                    "\t\t\ttr(segright[x], segright[x * 2], segright[x * 2 + 1]);\n" +
                    "\t\t}\n" +
                    "\t}; \n" +
                    "\tbuild(1, 1, n);\n" +
                    "\t\n" +
                    "\t\n" +
                    "\tfunction<void(int, int, int, int, int, int [], int &)> query = [&](int x, int l, int r, int s, int t, int nodeid[], int &curnodeid) -> void {\n" +
                    "\t\tif (s <= l && r <= t) {\n" +
                    "\t\t\tnodeid[curnodeid++] = x;\n" +
                    "\t\t} else {\n" +
                    "\t\t\tint mid = l + (r - l) / 2;\n" +
                    "\t\t\tif (s <= mid) query(x * 2, l, mid, s, t, nodeid, curnodeid);\n" +
                    "\t\t\tif (t > mid) query(x * 2 + 1, mid + 1, r, s, t, nodeid, curnodeid);\n" +
                    "\t\t}\n" +
                    "\t};\n" +
                    "\tvector<int> vis(n + 1);\n" +
                    "\tvector<int> seq;\n" +
                    "\tfunction<void(int)> dfs = [&](int x) -> void {\n" +
                    "\t\tif (vis[x]) return;\n" +
                    "\t\tvis[x] = 1;\n" +
                    "\t\tint curnodeid = 0;\n" +
                    "\t\tint nodeid[50];\n" +
                    "\t\tquery(1, 1, n, 1, x, nodeid, curnodeid);\n" +
                    "\t\tfor (int i = 0; i < curnodeid; i++) {\n" +
                    "\t\t\twhile (!segleft[nodeid[i]].empty() && p[x][1] - p[x][0] + d >= segleft[nodeid[i]].back()[0]) {\n" +
                    "\t\t\t\tint t = segleft[nodeid[i]].back()[1];\n" +
                    "\t\t\t\tsegleft[nodeid[i]].pop_back();\n" +
                    "\t\t\t\tdfs(t);\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t\tcurnodeid = 0;\n" +
                    "\t\tquery(1, 1, n, x, n, nodeid, curnodeid);\n" +
                    "\t\tfor (int i = 0; i < curnodeid; i++) {\n" +
                    "\t\t\twhile (!segright[nodeid[i]].empty() && p[x][1] + p[x][0] + d >= segright[nodeid[i]].back()[0]) {\n" +
                    "\t\t\t\tint t = segright[nodeid[i]].back()[1];\n" +
                    "\t\t\t\tsegright[nodeid[i]].pop_back();\n" +
                    "\t\t\t\tdfs(t);\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t\tseq.push_back(x);\n" +
                    "\t};\n" +
                    "\tfor (int i = 1; i <= n; i++) {\n" +
                    "\t\tdfs(i);\n" +
                    "\t}\n" +
                    "\tfor (int i = 0; i < segleft.size(); i++) {\n" +
                    "\t\tsegleft[i].clear();\n" +
                    "\t\tsegright[i].clear();\n" +
                    "\t}\n" +
                    "\tfunction<void(int, int, int)> build2 = [&](int x, int l, int r) -> void {\n" +
                    "\t\tif (l == r) {\n" +
                    "\t\t\tsegleft[x].push_back({p[l][1] + p[l][0] + d, l});\n" +
                    "\t\t\tsegright[x].push_back({p[l][1] - p[l][0] + d, l});\n" +
                    "\t\t} else {\n" +
                    "\t\t\tint mid = l + (r - l) / 2;\n" +
                    "\t\t\tbuild2(x * 2, l, mid);\n" +
                    "\t\t\tbuild2(x * 2 + 1, mid + 1, r);\n" +
                    "\t\t\tauto tr = [&](vector<array<int, 2>> &f, vector<array<int, 2>> &lc, vector<array<int, 2>> &rc) -> void {\n" +
                    "\t\t\t\tint i = 0, j = 0;\n" +
                    "\t\t\t\tf.reserve(lc.size() + rc.size());\n" +
                    "\t\t\t\twhile (i < lc.size() && j < rc.size()) {\n" +
                    "\t\t\t\t\tif (lc[i] <= rc[j]) {\n" +
                    "\t\t\t\t\t\tf.push_back(lc[i++]);\n" +
                    "\t\t\t\t\t} else {\n" +
                    "\t\t\t\t\t\tf.push_back(rc[j++]);\n" +
                    "\t\t\t\t\t}\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t\twhile (i < lc.size()) {\n" +
                    "\t\t\t\t\tf.push_back(lc[i++]);\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t\twhile (j < rc.size()) {\n" +
                    "\t\t\t\t\tf.push_back(rc[j++]);\n" +
                    "\t\t\t\t}\n" +
                    "\t\t\t};\n" +
                    "\t\t\ttr(segleft[x], segleft[x * 2], segleft[x * 2 + 1]);\n" +
                    "\t\t\ttr(segright[x], segright[x * 2], segright[x * 2 + 1]);\n" +
                    "\t\t}\n" +
                    "\t};\n" +
                    "\tbuild2(1, 1, n);\n" +
                    "\tint curcolor = 0;\n" +
                    "\tvector<int> color(n + 1);\n" +
                    "\t\n" +
                    "\tfunction<void(int)> dfs2 = [&](int x) -> void {\n" +
                    "\t\tif (color[x]) return;\n" +
                    "\t\tcolor[x] = curcolor;\n" +
                    "\t\tint curnodeid = 0;\n" +
                    "\t\tint nodeid[50];\n" +
                    "\t\tquery(1, 1, n, 1, x, nodeid, curnodeid);\n" +
                    "\t\tfor (int i = 0; i < curnodeid; i++) {\n" +
                    "\t\t\twhile (!segleft[nodeid[i]].empty() && p[x][1] + p[x][0] <= segleft[nodeid[i]].back()[0]) {\n" +
                    "\t\t\t\tint t = segleft[nodeid[i]].back()[1];\n" +
                    "\t\t\t\tsegleft[nodeid[i]].pop_back();\n" +
                    "\t\t\t\tdfs2(t);\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t\tcurnodeid = 0;\n" +
                    "\t\tquery(1, 1, n, x, n, nodeid, curnodeid);\n" +
                    "\t\tfor (int i = 0; i < curnodeid; i++) {\n" +
                    "\t\t\twhile (!segright[nodeid[i]].empty() && p[x][1] - p[x][0] <= segright[nodeid[i]].back()[0]) {\n" +
                    "\t\t\t\tint t = segright[nodeid[i]].back()[1];\n" +
                    "\t\t\t\tsegright[nodeid[i]].pop_back();\n" +
                    "\t\t\t\tdfs2(t);\n" +
                    "\t\t\t}\n" +
                    "\t\t}\n" +
                    "\t};\n" +
                    "\tfor (int i = n; i >= 1; i--) {\n" +
                    "\t\tif (!color[seq[i - 1]]) {\n" +
                    "\t\t\tcurcolor++;\n" +
                    "\t\t\tdfs2(seq[i - 1]);\n" +
                    "\t\t}\n" +
                    "\t}\n" +
                    "\t\n" +
                    "\tvector<int> siz(curcolor + 1);\n" +
                    "\tfor (int i = 1; i <= n; i++) {\n" +
                    "\t    siz[color[i]]++;\n" +
                    "\t}\n" +
                    "\tll ans = 0;\n" +
                    "\tfor (int i = 1; i <= curcolor; i++) {\n" +
                    "\t    ans += 1LL * siz[i] * siz[i];\n" +
                    "\t}\n" +
                    "\tcout << ans << endl;\n" +
                    "}\n" +
                    "\n" +
                    " \n" +
                    "int main(void) {\n" +
                    "    ios::sync_with_stdio(false);\n" +
                    "    cin.tie(0); cout.tie(0);\n" +
                    "    int t = 1;\n" +
                    "\t//cin >> t;\n" +
                    "    \n" +
                    "    while (t--) {\n" +
                    "    \tsolve(); \n" +
                    "\t}\n" +
                    "}\n");
            JudgeResult run = compiler.run(10000000000L, 100000000L, "1 1\n");
            System.out.println(run);
        }finally {
            compiler.removeAll();
        }

    }


    @Test
    void contextLoads() throws JsonProcessingException {
        ObjectMapper objectMapper=new ObjectMapper();
        System.out.println(objectMapper.writeValueAsString(problemService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compilerService.query().list()));
        System.out.println(objectMapper.writeValueAsString(compileParamService.query().list()));
        System.out.println(objectMapper.writeValueAsString(exampleService.query().list()));
    }


    String url="http://222.187.223.125:35811/run";
    @Test
    void Post() throws JsonProcessingException {
        JudgeParam param=new JudgeParam();
        JudgeParam.Cmd cmd=new JudgeParam.Cmd();
        cmd.setArgs(Arrays.asList("/usr/bin/g++","a.cc","-o","a"));
        cmd.setEnv(Arrays.asList("PATH=/usr/bin:/bin"));
        cmd.setFiles(Arrays.asList(
                new JudgeParam.MemoryFile(""),
                new JudgeParam.Collector("stdout",10240,false),
                new JudgeParam.Collector("stderr",10240,false)
                ));
        cmd.setCpuLimit(10000000000L);
        cmd.setMemoryLimit(104857600L);
        cmd.setProcLimit(50);
        cmd.getCopyIn().put("a.cc",new JudgeParam.MemoryFile("#include <iostream>\nusing namespace std;\nint main() {\nint a, b;\ncin >> a >> b;\ncout << a + b << endl;\n}"));
        cmd.setCopyOut(Arrays.asList("stdout", "stderr"));
        cmd.setCopyOutCached(Arrays.asList("a"));
        param.getCmd().add(cmd);
        ObjectMapper objectMapper=new ObjectMapper();
        RestTemplate restTemplate=new RestTemplate();

        ResponseEntity<String> response = restTemplate.postForEntity(url, objectMapper.writeValueAsString(param),String.class);
        objectMapper.readValue(response.getBody(), new TypeReference<List<JudgeResult>>() {
        });
        System.out.println(response.getBody());

    }

}
