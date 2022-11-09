import FusionCharts from "fusioncharts";
import Maps from "fusioncharts/fusioncharts.maps";
import Brazil from "fusionmaps/maps/fusioncharts.brazil";
import FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";
import ReactFC from "react-fusioncharts";


function MapBrazil(props) {

    var listMap = [];

    for(var i = 0; i < props.mapa.length; i++) {
        var dict = {
            id: "0"+i+1,
            value: ""+props.mapa[i].qt_reportes
        };

        listMap.push(dict);
        
    }

    console.log("aaaa", listMap);

    ReactFC.fcRoot(FusionCharts, Maps, Brazil, FusionTheme);

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
            showshadow: "0",
            legendposition: "BOTTOM",
            legendborderalpha: "0",
            legendbordercolor: "ffffff",
            legendallowdrag: "0",
            legendshadow: "0",
            caption: "Brazil",
            hoverFillalpha: "20",
            hovercolor: "#b3ffec",
            showborder: "1"
        },
        colorrange: {
            minvalue: "0",
            startlabel: "Low",
            endlabel: "High",
            code: "#e65c00",
            gradient: "1",
            color: [
                {
                    maxvalue: "2000",
                    displayvalue: "Average",
                    code: "#3ec34d"
                },
                {
                    maxvalue: "4000",
                    displayvalue: "Above Average",
                    code: "#80bfff"
                },
                {
                    maxvalue: "6000",
                    code: "#00b386"
                }
            ],
            maxvalue: 0
        },

        data: listMap
    };

    const chartConfigs = {
        type: "brazil",
        width: 300,
        height: 300,
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

