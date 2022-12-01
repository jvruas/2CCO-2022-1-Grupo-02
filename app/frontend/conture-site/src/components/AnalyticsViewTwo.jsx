import React from "react";
import { Chart } from "react-google-charts";

export const options = {
  title: "Quantidade de Furtos vs. Quantidade de visualizações dos produtos na plataforma",
  hAxis: { title: "Furtos", minValue: 0, maxValue: 4000},
  vAxis: { title: "Visualizações", minValue: 0},
  legend: "TESTE",
  trendlines: {
    0: {
      type: "exponential",
      visibleInLegend: true,
    },
  },
};

function AnalyticsViewTwo(props) {
  const dataView = [["Furtos", "Visualizações"]];
  console.log(props.dados);
  console.log(props.dados2);
  for (var idx = 0; idx < props.dados2.length; idx++) {
    dataView.push([props.dados[idx].qtFurtos, props.dados2[idx].qtVisualizacao]);
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

export default AnalyticsViewTwo;
