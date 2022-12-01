// import FusionCharts from "fusioncharts";
// import Maps from "fusioncharts/fusioncharts.maps";
// import Brazil from "fusionmaps/maps/fusioncharts.brazil";
// import FusionTheme from "fusioncharts/themes/fusioncharts.theme.fusion";
// import ReactFC from "react-fusioncharts";


// function MapBrazil() {


//     ReactFC.fcRoot(FusionCharts, Maps, Brazil, FusionTheme);

//     const dataSource = {
//         chart: {
//             bgColor: "#FFFFFF",
//             bgAlpha: "0",
//             animation: "0",
//             showLabels: "0",
//             usehovercolor: "1",
//             canvasbordercolor: "FFFFFF",
//             bordercolor: "#aaa",
//             showlegend: "0",
//             showshadow: "0",
//             legendposition: "BOTTOM",
//             legendborderalpha: "0",
//             legendbordercolor: "ffffff",
//             legendallowdrag: "0",
//             legendshadow: "0",
//             caption: "Brazil",
//             hoverFillalpha: "20",
//             hovercolor: "#b3ffec",
//             showborder: "1"
//         },
//         colorrange: {
//             minvalue: "0",
//             startlabel: "Low",
//             endlabel: "High",
//             code: "#e65c00",
//             gradient: "1",
//             color: [
//                 {
//                     maxvalue: "200000",
//                     displayvalue: "Average",
//                     code: "#3ec34d"
//                 },
//                 {
//                     maxvalue: "400000",
//                     displayvalue: "Above Average",
//                     code: "#80bfff"
//                 },
//                 {
//                     maxvalue: "600000",
//                     code: "#00b386"
//                 }
//             ],
//             maxvalue: 0
//         },

//         data: [
//             {
//                 id: "002",
//                 value: "4000"
//             },
//             {
//                 id: "001",
//                 value: "4000"
//             }
//         ]
//     };

//     const chartConfigs = {
//         type: "brazil",
//         width: 300,
//         height: 300,
//         dataFormat: "json",
//         dataSource: dataSource
//     };

//     return (
//         <>
//             <ReactFC {...chartConfigs} />
//         </>
//     )
// }

// export default MapBrazil;

