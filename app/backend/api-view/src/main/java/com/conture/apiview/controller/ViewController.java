package com.conture.apiview.controller;

import org.springframework.http.ResponseEntity;
import static org.springframework.http.ResponseEntity.*;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.conture.apiview.repository.ViewBaseFurtosRepository;
import com.conture.apiview.repository.ViewDoacaoReporitory;
import com.conture.apiview.repository.ViewFurtosProdutosRepository;
import com.conture.apiview.repository.ViewFurtosQtdUsuariosRepository;
import com.conture.apiview.repository.ViewFurtosVisualizacaoRepository;
import com.conture.apiview.repository.ViewProdutosVendidosDoadosRepository;
import com.conture.apiview.repository.ViewQtdProdutosEstadoRepository;
import com.conture.apiview.repository.ViewQtdReportesEstadoRepository;
import com.conture.apiview.repository.ViewQtdRepository;
import com.conture.apiview.repository.ViewReporteRepository;
import com.conture.apiview.repository.ViewTodasDatasRepository;

import org.springframework.beans.factory.annotation.Autowired;

@RestController
@RequestMapping("/view")
@CrossOrigin(allowedHeaders = "*")
public class ViewController {

    @Autowired
	private ViewReporteRepository vwReporteRepository;

	@Autowired
	private ViewQtdRepository vwQtdRepository;

	@Autowired
	private ViewProdutosVendidosDoadosRepository vwProdutosVendidosDoadosRepository;

	@Autowired
	private ViewDoacaoReporitory vwDoacaoReporitory;

	@Autowired
	private ViewQtdReportesEstadoRepository vwQtdReportesEstadoRepository;

	@Autowired
	private ViewQtdProdutosEstadoRepository vwQtdProdutosEstadoRepository;

	@Autowired
	private ViewTodasDatasRepository vwTodasDatasRepository;

	@Autowired
	private ViewFurtosProdutosRepository vwFurtosProdutosRepository;

	@Autowired
	private ViewFurtosVisualizacaoRepository vwFurtosVisualizacaoRepository;

	@Autowired
	private ViewFurtosQtdUsuariosRepository vwFurtosQtdUsuariosRepository;

	@Autowired
	private ViewBaseFurtosRepository vwBaseFurtosRepository;

	@GetMapping("/busca_datas")
	public ResponseEntity listaDatas(){
		return status(200).body(vwTodasDatasRepository.findTeste());
	}

    @GetMapping("/vw_reporte_tipo_reporte")
	public ResponseEntity listarReportes(@RequestParam String data) {
		return status(200).body(vwReporteRepository.findTeste(data));
	}
    
	@GetMapping("/vw_qtd_desligamento_motivo_desligamento")
	public ResponseEntity listaQtdDesligamentos(@RequestParam String data) {
		return status(200).body(vwQtdRepository.findTeste(data));
	}

	@GetMapping("/vw_produtos_vendidos_doados")
	public ResponseEntity listaProdutosVendidosDoados(@RequestParam String data) {
		return status(200).body(vwProdutosVendidosDoadosRepository.findTeste(data));
	}

	@GetMapping("/vw_doacao_historica")
	public ResponseEntity listaDoacaoHistorica(@RequestParam String data) {
		return status(200).body(vwDoacaoReporitory.findTeste(data));
	}

	@GetMapping("/vw_qtd_doacao_estado")
	public ResponseEntity listaDoacaoEstado(@RequestParam String data) {
		return status(200).body(vwQtdProdutosEstadoRepository.findTeste(data));
	}

	@GetMapping("/vw_qtd_reportes_estado")
	public ResponseEntity listaReportesEstado(@RequestParam String data) {
		return status(200).body(vwQtdReportesEstadoRepository.findTeste(data));
	}

	// Analytics
	@GetMapping("/vw_base_furtos")
	public ResponseEntity listaFurtos(@RequestParam String data) {
		return status(200).body(vwBaseFurtosRepository.findTeste(data));
	}

	@GetMapping("/vw_furtos_por_qt_produtos")
	public ResponseEntity listaFurtosProdutos(@RequestParam String data) {
		return status(200).body(vwFurtosProdutosRepository.findTeste(data));
	}

	@GetMapping("/vw_furtos_por_qt_visualizacao")
	public ResponseEntity listaFurtosVisualizacao(@RequestParam String data) {
		return status(200).body(vwFurtosVisualizacaoRepository.findTeste(data));
	}

	@GetMapping("/vw_furtos_por_qt_usuarios")
	public ResponseEntity listaFurtosUsuarios(@RequestParam String data) {
		return status(200).body(vwFurtosQtdUsuariosRepository.findTeste(data));
	}
}
