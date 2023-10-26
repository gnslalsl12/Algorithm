const readline = require('readline');
const rl = readline.createInterface({
  input: process.stdin,
  output: process.stdout
});

let N = -1;
let M = -1;
let L = -1;
const Road = [];
let Result = 0;

function setRoadSorted(){
    Road[0].sort((x1, x2) => x1 - x2);
    let before = +0;
    for(r of Road[0]){
        Road.push(r - before);
        before = r;
    }
    Road.push(L-before);
    Road.shift();
    Road.sort((x1,x2) => x1-x2);
}

function getMinDiv(){
    let min = 1;
    let max = L-1;
    let dist = 0;
    while(min <= max){
        dist = Math.floor((max+min)/2);
        let needCount = 0;
        for(r of Road){
            needCount += Math.floor(r/dist) - (r%dist === 0? 1 : 0);
        }
        if(needCount > M){  //더 간격을 놀려야함
            min = dist+1;
        }else if(needCount < M){    //더 간격을 줄여야함함
            max = dist-1;
        }else{  //개수가 맞음
            max = dist-1;
        }
    }
    Result = min;
}

function getMinWithM(){
    Result = Math.floor(L/(M+1)) + (L%(M+1) === 0? 0 : 1);
}

function solv(){
    setRoadSorted();
    N === 0? getMinWithM() : getMinDiv();
}

rl.on('line', function(line){
    if(N === -1){
        const inputNums = line.split(' ');
        N = parseInt(inputNums[0]);
        M = parseInt(inputNums[1]);
        L = parseInt(inputNums[2]);
    }else{
        const inputArr = line.split(' ').map((inputNum) => {
            return parseInt(inputNum);
        })
        Road.push(inputArr);
        rl.close();
    }
    
}).on('close', function(){
    solv();
    console.log(Result);
    process.exit();
})