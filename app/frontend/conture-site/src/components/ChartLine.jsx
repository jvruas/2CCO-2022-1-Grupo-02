import { Chart } from "react-google-charts";
import "../html-css-template/css/Dashboard.css";

export const dataLine = [
  ["Ano", "Presente", "Passado"],
  ["2019", 100, 400],
  ["2020", 117, 460],
  ["2021", 660, 120],
  ["2022", 103, 540],
];  

export const optionsLine = {
  title: "Qtd. doações Presente X Qtd. doações Passado",
  colors: ["#470083", "#0066FF"],
//   backgroundColor: "#f0efef",
  hAxis: { title: "Ano", titleTextStyle: { color: "#3334" } },
  vAxis: { minValue: 0 },
  titleTextStyle: {
    color: 'black',    
    fontName: 'Tajawal', 
    fontSize: 14, 
    bold: true, 
    italic: false 
}
};

const chartsLine = () => {
  return (
    <div className="card">
      <Chart
        chartType="AreaChart"
        data={dataLine}
        options={optionsLine}
        width={"100%"}
        height={"100%"}
      />
    </div>
  );
};
export default chartsLine;
