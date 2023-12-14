let Text;
let Result;

function init(line) {
  Text = line;
  Result = 0;
}

function getLpsArray(tempText) {
  //tempText의 KMP 부분배열 구하기
  const N = tempText.length;
  const lpsArray = new Array(N).fill(0);
  let i = 1;
  let len = 0;
  while (i < N) {
    if (tempText.charAt(i) === tempText.charAt(len)) {
      lpsArray[i] = ++len;
      Result = Math.max(Result, lpsArray[i++]); //접두사 ,접미사를 두 부분문자열로 여겨 최고길이값 갱신
    } else {
      if (len != 0) {
        len = lpsArray[len - 1];
      } else {
        lpsArray[i] = 0;
        i++;
      }
    }
  }
}

function solv() {
  const tN = Text.length;
  for (let i = 0; i < tN; i++) {
    if (Result >= tN - i) break;
    getLpsArray(Text.slice(i, tN));
  }
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