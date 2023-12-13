let N, K;
let DP;
let Result;

function init(line) {
  [N, K] = line.split(" ").map(Number);
  DP = Array.from(Array(N + 1), () => Array(K).fill(1));
  Result = 0;
}

function getResult() {
  for (let n = 1; n <= N; n++) {
    for (let k = 1; k < K; k++) {
      DP[n][k] = (DP[n - 1][k] + DP[n][k - 1]) % 1000000000;
    }
  }
  Result = DP[N][K - 1];
}

function solv() {
  getResult();
  console.log(Result);
}

require("readline")
  .createInterface(process.stdin, process.stdout)
  .on("line", (line) => {
    init(line);
  })
  .on("close", () => {
    solv();
    process.exit();
  });