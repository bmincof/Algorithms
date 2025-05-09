#include <iostream>

using namespace std;

long long mod = 1'000'000'007;
long long factorial[4'000'001];

long long pow(long long num, long long exp) {
    if (exp == 1) {
        return num;
    }

    long long result = pow(num, exp / 2);
    result = (result * result) % mod;
    if (exp % 2 == 1) result = (result * num) % mod;

    return result;
}

int main() {
    ios::sync_with_stdio(0);
    cin.tie(0);
    cout.tie(0);

    factorial[0] = 1;
    for (int i = 1; i < 4'000'001; ++i) {
        factorial[i] = (i * factorial[i - 1]) % mod;
    }

    // a^p === a mod p
    // a^p-2 === a^-1 mod p
    // C(n, k) = n! / k!(n-k)! === n!(k!(n-k)!)^p-2 mod p
    int M, N, K;

    cin >> M;

    while (M--) {
        cin >> N >> K;

        long long num = (factorial[K] * factorial[N - K]) % mod;
        long long denominator = pow(num, mod - 2);
        cout << (factorial[N] * denominator) % mod << '\n';
    }

    return 0;
}