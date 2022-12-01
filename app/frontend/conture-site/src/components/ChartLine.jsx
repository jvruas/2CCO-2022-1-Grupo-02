import { Chart } from "react-google-charts";
import "../html-css-template/css/Dashboard.css";

export const options = {
  title: "Doações históricas (Presente X Passado)",
  vAxis: { title: "Doações" },
  hAxis: { title: "Ano" },
  colors: ["#734D84", "#8084D5", "#979BFA"], 
  seriesType: "bars",
  series: { 5: { type: "line" } },
};


function chartsLine(props) {
  const dataLine = [["Ano", "Headset", "Mesa Digitalizadora", "Notebook", "Smartphone", "Tablet", "Media"]];
  console.log(props.dados);
  for (var idx = 0; idx < props.dados.length; idx++) {
    var media = (props.dados[idx].headset + props.dados[idx].mesaDigitalizadora + props.dados[idx].notebook + props.dados[idx].smartphone + props.dados[idx].tablet)/6
    dataLine.push([props.dados[idx].ano, props.dados[idx].headset, props.dados[idx].mesaDigitalizadora, props.dados[idx].notebook, props.dados[idx].smartphone, props.dados[idx].tablet, media]);
  }

  console.log("aaaabbbb", dataLine);

  return (
    <div className="card">
      <Chart
        chartType="ComboChart"
        data={dataLine}
        options={options}
        width={"1000px"}
        height={"400px"}
      />
    </div>
  );
}

export default chartsLine;
