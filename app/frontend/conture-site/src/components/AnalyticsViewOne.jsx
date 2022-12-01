import React from "react";
import { Chart } from "react-google-charts";

export const options = {
  title: "Quantidade de Furtos vs. Doações realizadas na plataforma",
  hAxis: { title: "Furtos", minValue: 0, maxValue: 4000},
  vAxis: { title: "Doações Realizadas", minValue: 0},
  legend: "TESTE",
  trendlines: {
    0: {
      type: "exponential",
      visibleInLegend: true,
    },
  },
};

function AnalyticsViewOne(props) {
  const dataView = [["Furtos", "Doações"]];
  console.log(props.dados);
  console.log(props.dados2);
  for (var idx = 0; idx < props.dados2.length; idx++) {
    dataView.push([props.dados[idx].qtFurtos, props.dados2[idx].qtProdutos]);
  } 

  return (
    <Chart
      chartType="ScatterChart"
      width="80%"
      height="400px"
      data={dataView}
      options={options}
    />
  );
}

export default AnalyticsViewOne;
