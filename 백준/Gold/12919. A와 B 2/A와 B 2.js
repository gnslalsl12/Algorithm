const readline = require("readline");
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout,
});

let S;
let T;
let Result = 0;

function getResult(inputT) {
  if (inputT === S || Result === 1) {
    //T == S라면 완성
    Result = 1;
    return;
  } else if (inputT.length === S.length) {
    //길이가 같아지면 끝
    return;
  }

  if (inputT.charAt(0) === "B") {
    //맨 앞이 B (S에서 B를 추가하고 뒤집은 경우의 가정)
    getResult(inputT.substring(1, inputT.length).split("").reverse().join(""));
  }

  if (inputT.charAt(inputT.length - 1) === "A") {
    getResult(inputT.substring(0, inputT.length - 1));
  }
}

function solv() {
  getResult(T);
  console.log(Result);
}

rl.on("line", function (line) {
  if (S === undefined) {
    S = line;
  } else {
    T = line;
    rl.close();
  }
}).on("close", function () {
  solv();
  process.exit();
});