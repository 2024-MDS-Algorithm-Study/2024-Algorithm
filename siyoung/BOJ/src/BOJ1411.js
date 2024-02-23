const fs = require('fs');
// const input = fs.readFileSync("/dev/stdin").toString().trim().split('\n');
const input = fs.readFileSync("./input.txt").toString().trim().split('\n');
const n = parseInt(input[0]);
let ret = 0;
for (let i=1; i<=n; i++) {
    let cur = input[i].trim();
    L: for (let j=i+1; j<=n; j++) {
        let tmp = input[j].trim();
        let v = [];
        let map = new Map();
        for (let k=0; k<cur.length; k++) {
            let curC = cur[k]
            let tmpC = tmp[k];
            if (map.get(curC) && map.get(curC)!==tmpC) continue L;
            if (!map.get(curC) && v.includes(tmpC)) continue L;
            if (!map.get(curC) && !v.includes(tmpC)) {
                map.set(curC, tmpC);
                v.push(tmpC);
            }
        }
        ret++;
    }
}
console.log(ret);