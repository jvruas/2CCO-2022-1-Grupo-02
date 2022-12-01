import { Chart } from "react-google-charts";

export const options = {
  title:
    "Produtos mais Doados X Produtos mais Visualizados",
  subtilte: "Comparativo entre os produtos mais doados com os produtos mais visualizados em nossa plataforma.",
  hAxis: { title: "Doações" },
  vAxis: { title: "Visualizações" },
  colors: ["#734D84", "#8084D5", "#979BFA"], 
  bubble: {
    textStyle: {
      fontSize: 9,  
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

  console.log("AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAASSPINTO", props.dados);

  for (let index = 0; index < props.dados.length; index++) {
      var temp = [(props.dados[index].nome).charAt(0)+(props.dados[index].nome).toLowerCase().slice(1), props.dados[index].qtdProdutos, props.dados[index].qtdVisualizacao];
      data.push(temp);
  }

  console.log(data);
  return (
    <Chart
      chartType="BarChart"
      data={data}
      options={options}
      width={"100%"}
      height={"100%"}
    />
  );
};
export default chartBarViewTwo;
