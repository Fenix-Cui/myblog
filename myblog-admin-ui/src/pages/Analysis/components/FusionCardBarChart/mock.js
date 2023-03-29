import axios from "axios";
import {Message} from "@alifd/next";

// window.onload = async function () {
//   window.b = await getValue();
//   console.log(b)
// }
// const getValue = async () => {
//   const res = await axios.get("http://localhost:9000/http/main/getDayVisits");
//
//   return res.data.value;
// };

export default {
  // value: window.b,//访问量
  saleList: [
    {date: 1, value: 1},
    {date: 2, value: 2},
    {date: 3, value: 3},
    {date: 4, value: 4},
    {date: 5, value: 5},
    {date: 6, value: 6},
    {date: 7, value: 7},
    // { date: 8, value: 7 },
  ],
  dailySale: '¥1,234',
};
