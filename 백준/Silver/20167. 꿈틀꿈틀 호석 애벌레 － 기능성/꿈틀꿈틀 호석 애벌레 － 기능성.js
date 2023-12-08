let N;
let K;
let Haps;

function init(line) {
  if (N === undefined) {
    [N, K] = line.split(" ").map(Number);
  } else {
    Haps = line.split(" ").map(Number);
  }
}

function getResult() {
  const dpTable = Array(N + 1).fill(0);
  let start = 0;
  let stack = Haps[0];
  for (let end = 1; end <= N; end++) {
    dpTable[end] = dpTable[end - 1];
    while (stack >= K) {
      dpTable[end] = Math.max(dpTable[end], dpTable[start] + (stack - K));
      stack -= Haps[start++];
    }
    stack += Haps[end];
  }
  console.log(dpTable[N]);
}

function solv() {
  getResult();
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