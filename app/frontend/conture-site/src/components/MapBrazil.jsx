import FusionCharts from "fusioncharts";
import Maps from "fusioncharts/fusioncharts.maps";
import Brazil from "fusionmaps/maps/fusioncharts.brazil";
import FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";
import ReactFC from "react-fusioncharts";

function MapBrazil(props) {
    var listMap = [];
    for (var i = 0; i < props.mapa.length; i++) {
        var dictId = {
            AC: "001", AL: "002", AP: "003", AM: "004", BA: "005", CE: "006", DF: "007", ES: "008", GO: "009", MA: "010", MT: "011", MS: "012", MG: "013", PA: "014", PB: "015", PR: "016", PE: "017", PI: "018", RJ: "019", RN: "020", RS: "021", RO: "022", RR: "023", SC: "024", SP: "025", SE: "026", TO: "027"
        };
        var dict = {
            id: dictId[`${props.mapa[i].uf}`],
            value: "" + props.mapa[i].qt_reportes
        };
        listMap.push(dict);
    }
    console.log("aaaa", listMap);
    // ReactFC.fcRoot(FusionCharts, Maps, Brazil, FusionTheme);
    const dataSource = {
        chart: {
            bgColor: "#FFFFFF",
            bgAlpha: "0",
            animation: "0",
            showLabels: "0",
            usehovercolor: "1",
            canvasbordercolor: "FFFFFF",
            bordercolor: "#aaa",
            showlegend: "0",
            showshadow: "1",
            legendposition: "BOTTOM",
            legendborderalpha: "0",
            legendbordercolor: "ffffff",
            legendallowdrag: "0",
            legendshadow: "1",
            caption: "Brazil",
            hoverFillalpha: "20",
            hovercolor: "#b3ffec",
            showborder: "1"
        },
        colorrange: {
            minvalue: "0",
            startlabel: "Low",
            endlabel: "High",
            code: "#c60000",
            gradient: "3",
            color: [
                {
                    maxvalue: "200",
                    displayvalue: "Average",
                    code: "#3ec34d"
                },
                {
                    maxvalue: "400",
                    displayvalue: "Above Average",
                    code: "#80bfff"
                },
                {
                    maxvalue: "600",
                    code: "#00b386"
                }
            ],
            maxvalue: 0
        },
        data: listMap
    };
    const chartConfigs = {
        type: "brazil",
        width: 500,
        height: 500,
        dataFormat: "json",
        dataSource: dataSource
    };
    return (
        <>
            <ReactFC {...chartConfigs} />
        </>
    )
}
export default MapBrazil;