import { Chart } from "react-google-charts";
import "../html-css-template/css/Dashboard.css";

export const options = {
  title: "Monthly Coffee Production by Country",
  vAxis: { title: "Cups" },
  hAxis: { title: "Month" },
  seriesType: "bars",
  series: { 5: { type: "line" } },
};


function chartsLine(props) {
  const dataLine = [["Ano", "Headset", "Mesa Digitalizadora", "Notebook", "Smartphone", "Tablet", "Average"]];
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
        width={"100%"}
        height={"100%"}
      />
    </div>
  );
}

export default chartsLine;
