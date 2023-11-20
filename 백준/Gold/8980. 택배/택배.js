const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let N; //마을 수 (2 ~ 2,000)
let C; //트럭 용량 (1 ~ 10,000)
let M; //박스 정보 개수(1 ~ 10,000);
const Infos = []; //M개의 정보들
let Result = 0;

function setSortInfos() {
  Infos.sort((x1, x2) => {
    if (x1.from === x2.from) {
      return x1.to - x2.to;
    }
    return x1.from - x2.from;
  });
}

function setDelivered() {
  let index = 0;
  const countSums = Array(N + 1).fill(0);
  while (index < M) {
    const from = Infos[index].from;
    const to = Infos[index].to;
    const count = Infos[index].count;

    let maxSum = 0; //from ~ to 구간에서 가장 많이 갖게 되는 박스 개수
    for (let spot = from; spot < to; spot++) {
      maxSum = Math.max(maxSum, countSums[spot]);
    }

    //실질적으로 from ~ to 구간동안 가져갈 수 있는 박스 수
    const ableAdd = Math.min(C - maxSum, count);
    //해당 구간에서 가장 큰 박스개수가 많은 구간을 고려해
    //그 나머지만큼 가져가는 만큼과, (박스들 중 일부만 배송할 수도 있으니)
    //현재 실으려는 박스의 수 중 최솟값으로 갱신
    Result += ableAdd;
    for (let spot = from; spot < to; spot++) {
      countSums[spot] += ableAdd;
    }
    index++;
  }
}

function solv() {
  setSortInfos();
  setDelivered();
  console.log(Result);
}

rl.on("line", function (line) {
  if (N === undefined) {
    const inputLine = line.split(" ");
    N = parseInt(inputLine[0]);
    C = parseInt(inputLine[1]);
  } else if (M === undefined) {
    M = parseInt(line);
  } else {
    const inputLine = line.split(" ");
    if (inputLine[2] <= C) {
      Infos.push({
        from: parseInt(inputLine[0]),
        to: parseInt(inputLine[1]),
        count: parseInt(inputLine[2]),
      });
    }
    if (Infos.size === M) rl.close();
  }
}).on("close", function () {
  solv();
  process.exit();
});