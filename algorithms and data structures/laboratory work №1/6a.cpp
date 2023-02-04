#include <bits/stdc++.h>
 
using namespace std;
 
//#define int int64_t
 
char s[10'000'001];
 
void shift(char& c) {
    if (c == '9') {
        c = '0';
    } else {
        c++;
    }
}
 
int32_t main() {
    ios_base::sync_with_stdio(0);
    //cin.tie(0); cout.tie(0);
    int n;
    scanf("%d", &n);
    scanf("%s", s);
    deque<char> a, b;
    char cur = '1';
    bool open_a = true;
    bool open_b = true;
    for (int i = 0; i < n; i++) {
        if (open_a && open_b) {
            if (s[i] == 'a') {
                a.push_back(cur);
                shift(cur);
            } else if (s[i] == 'b') {
                b.push_front(cur);
                shift(cur);
            } else if (s[i] == 'A') {
                printf("%c", a.front());
                a.pop_front();
            } else if (s[i] == 'B') {
                printf("%c", b.back());
                b.pop_back();
            } else if (s[i] == '>') {
                open_a = false;
            } else {
                open_b = false;
            }
        } else if (open_a) {
            if (s[i] == 'a') {
                b.push_back(cur);
                shift(cur);
            } else if (s[i] == 'A') {
                if (!a.empty()) {
                    printf("%c", a.front());
                    a.pop_front();
                } else {
                    printf("%c", b.front());
                    b.pop_front();
                }
            } else {
                open_b = true;
                int sz = (a.size() + b.size()) / 2;
                while (b.size() < sz) {
                    b.push_front(a.back());
                    a.pop_back();
                }
                while (b.size() > sz) {
                    a.push_back(b.front());
                    b.pop_front();
                }
            }
        } else {
            if (s[i] == 'b') {
                a.push_front(cur);
                shift(cur);
            } else if (s[i] == 'B') {
                if (!b.empty()) {
                    printf("%c", b.back());
                    b.pop_back();
                } else {
                    printf("%c", a.back());
                    a.pop_back();
                }
            } else {
                open_a = true;
                int sz = (a.size() + b.size()) / 2;
                while (a.size() > sz) {
                    b.push_front(a.back());
                    a.pop_back();
                }
                while (a.size() < sz) {
                    a.push_back(b.front());
                    b.pop_front();
                }
            }
        }
    }
}