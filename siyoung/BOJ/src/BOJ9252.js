const fs = require('fs');
const input = fs.readFileSync('./input.txt').toString().trim().split('\n');
// const input = fs.readFileSync('/dev/stdin').toString().trim().split('\n');

let str1 = input[1].replace(/[^A-Z]/g, '');
let str2 = input[0].replace(/[^A-Z]/g, '');

let dp = Array.from(Array(str1.length+1), () => new Array(str2.length+1).fill(0));

for(let i=1; i<=str1.length; i++){

    for(let j=1; j<=str2.length; j++){
        if (str1.charAt(i-1)===str2.charAt(j-1)) dp[i][j] = Math.max(dp[i-1][j-1]+1, dp[i][j-1]);
        else dp[i][j] = Math.max(dp[i-1][j], dp[i][j-1]);
    }
}
// for (const subArray of dp) {
//     console.log(subArray.join(','));
// }
let ret = []
let jsize = str2.length
L: for(let i=str1.length; i>0; i--){
    for(let j=jsize; j>0; j--){
        if(dp[i][j]===dp[i][j-1]) continue;
        else{
            if(dp[i][j] !== dp[i-1][j]){
                ret.push(str2.charAt(j-1));
                jsize = j;
                break;
            }
            else{
                jsize = j;
                continue L;
            }
        }

    }
}
console.log(dp[str1.length][str2.length])
console.log(ret.reverse().join(''));