import SideBar from "../components/SideBar";
import "../html-css-template/css/Dashboard.css";

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
            <div className="card-view">C</div>
            <div className="card-view">U</div>
          </div>

          <div className="container-inferior">
            <div className="card-view">PIRO</div>
            <div className="card-view">CA</div>
          </div>
        </div>
      </section>
    </div>
  );
}

export default Dashboard;
