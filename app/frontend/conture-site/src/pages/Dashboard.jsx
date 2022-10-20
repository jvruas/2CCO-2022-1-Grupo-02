import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";
import ChartLine from "../components/ChartLine";
import ChartBarViewTwo from "../components/ViewTwo";
import ChartBarViewThree from "../components/ViewThree";
import ChartBarViewFour from "../components/ViewFour";

function Dashboard() {
  return (
    <div id="dash">
      <SideBar />
      <section id="dash-pUm">
        <div className="title-bi">
          <h1>GRÁFICOS BI - VISÃO GERAL</h1>
        </div>
        <div className="dash-pDois">
          <div className="dash-scroll">
            <ChartLine />
            <ChartBarViewTwo />
            <ChartBarViewThree />
            <ChartBarViewFour />
          </div>
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
