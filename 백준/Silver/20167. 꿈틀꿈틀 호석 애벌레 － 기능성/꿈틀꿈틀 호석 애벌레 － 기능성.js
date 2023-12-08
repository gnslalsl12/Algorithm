let N;
let K;
let Haps;
let DpTable;

function init(line) {
  if (N === undefined) {
    [N, K] = line.split(" ").map(Number);
    DpTable = Array.from(Array(N), () => Array(2).fill(-1));
  } else {
    Haps = line.split(" ").map(Number);
  }
}

function setDpTable() {
  let stack = 0;
  let index = 0;
  for (let n = 0; n < N; n++) {
    stack += Haps[n];
    if (stack >= K) {
      DpTable[n] = [stack - K, index];
      while (stack >= K) {
        stack -= Haps[index++];
      }
    }
  }
}

function getResult() {
  let result = 0;
  let start = N;
  let max = 0;
  for (let n = N - 1; n >= 0; n--) {
    if (start > n) {
      max = 0;
    }
    if (DpTable[n][0] > max) {
      if (start <= n) {
        //겹치는 부분
        result -= max;
      }
      [max, start] = DpTable[n];
      result += max;
    }
  }
  console.log(result);
}

function solv() {
  setDpTable();
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