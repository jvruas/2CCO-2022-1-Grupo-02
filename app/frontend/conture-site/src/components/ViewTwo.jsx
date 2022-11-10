import { Chart } from "react-google-charts";

export const options = {
  title:
    "Produtos mais Doados X Produtos mais Visualizados",
  subtilte: "Comparativo entre os produtos mais doados com os produtos mais visualizados em nossa plataforma.",
  hAxis: { title: "Visualizações" },
  vAxis: { title: "Doações" },
  colorAxis: { colors: ["purple", "red"] },
  bubble: {
    textStyle: {
      fontSize: 11,
      fontName: "Tajawal",
      color: "black",
      bold: true,
      italic: true,
      auraColor: "none",
    },
  },
};

function chartBarViewTwo(props){

  var data = [
    ["Produto", "Doações", "Visualizações"]
  ];

  console.log(props.dados);

  for (let index = 0; index < props.dados.length; index++) {
      var temp = [props.dados[index].produto, props.dados[index].qtdProdutos, props.dados[index].qtdVisualizacao];
      data.push(temp);
  }

  console.log(data);
  return (
    <Chart
      chartType="BubbleChart"
      data={data}
      options={options}
      width={"100%"}
      height={"100%"}
    />
  );
};
export default chartBarViewTwo;
