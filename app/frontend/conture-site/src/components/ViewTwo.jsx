import { Chart } from "react-google-charts";
export const data = [
  ["Ano", "Notebook", "Celular", "Desktop"],
  ["2019", 1000, 400, 200],
  ["2020", 1170, 460, 250],
  ["2021", 660, 1120, 300],
  ["2022", 1030, 540, 350],
];

export const optionsBar = {
    chart: {
        title: "Produtos mais Doados X Produtos mais Visualizados",
        backgroundColor: {
            fill: "rgba(255, 255, 255)",
        },
    },
    chartArea: { backgroundColor: '#f1f7f9' },
    titleTextStyle: {
        color: "black",
        fontName: "Tajawal",
        fontSize: 18,
        bold: true,
        italic: false,
      },
    backgroundColorfill: "#f0efef",
    colors: ["#875B9B", "#979BFA", "#734AE7"],

};

const chartBarViewTwo = () => {
  return (
    <Chart
      chartType="Bar"
      data={data}
      options={optionsBar}
      width={"100%"}
      height={"100%"}
    />
  );
};
export default chartBarViewTwo;
