import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";
import { Chart } from "react-google-charts";

export const data = [
  ["Year", "Sales", "Expenses", "Profit"],
  ["2014", 1000, 400, 200],
  ["2015", 1170, 460, 250],
  ["2016", 660, 1120, 300],
  ["2017", 1030, 540, 350],
];

export const options = {
  chart: {
    title: "Company Performance",
    subtitle: "Sales, Expenses, and Profit: 2014-2017",
  },
};

function Dashboard() {
  return (
    <div>
      <SideBar />
      <section id="mae">
        <div className="pai">
          <div className="title-bi">
            <h1>Gráfico BI - Visão Geral</h1>
          </div>
          <hr />
          <div className="container-superior">
          <Chart
              chartType="Bar"
              width="70%"
              height="400px"
              data={data}
              options={options}
            />

            <Chart
              chartType="Bar"
              width="10%"
              height="1000px"
              data={data}
              options={options}
            />

          </div>

          <div className="container-inferior">
            <Chart
              chartType="Bar"
              width="70%"
              height="400px"
              data={data}
              options={options}
            />

            <Chart
              chartType="Bar"
              width="70%"
              height="400px"
              data={data}
              options={options}
            />
          </div>
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
