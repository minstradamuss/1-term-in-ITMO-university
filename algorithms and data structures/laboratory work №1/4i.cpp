#include <iostream>
#include <deque>
 
using namespace std;
 
int main()
{
    ios::sync_with_stdio(0);
    //cin.tie(0);
    deque <long long int> q;
    int n;
    cin >> n;
    long long int pred_pr = 0;
    for (int i = 0; i < n; i++) {
        int command;
        cin >> command;
        if (command == 1) {
            long long int a;
            cin >> a;
            q.emplace_front(a - pred_pr);
        } else if (command == 2) {
            long long int x, y;
            cin >> x >> y;
            pred_pr += y;
            q[q.size() - 1] += x - y;
        } else if (command == 3) {
            cout << (q.back() + pred_pr) << endl;
            q.pop_back();
        }
    }
    return 0;
}