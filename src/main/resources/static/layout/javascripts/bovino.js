$('#confirmacaoExclusaoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	
	window.alert(action);
	form.attr('action', action + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricaoTitulo + '</strong>?');
});

$('#confirmacaoExclusaoModalTarefa').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	var codigoTitulo = button.data('codigo');
	var tipo = button.data('tipo');
	var descricaoTitulo = button.data('descricao');
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	
//	var url = action + tipo +"/" + codigoTitulo
//	window.alert(url);
	form.attr('action', action + tipo +"/" + codigoTitulo);
	
	modal.find('.modal-body span').html('Tem certeza que deseja excluir <strong>' + descricaoTitulo + '</strong>?');
});




$('#editarEccModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	
	var uri = button.data('uri');
	var idEcc = button.data('id')
	var escore = button.data('escore')
	var status = button.data('status')
	var datai = button.data('dataInclusao')
	var descricaoTitulo = button.data('descricao');

	//window.alert(status);
	$(".ecc_id").val(idEcc);
	$(".ecc_status").val(status);
	$(".ecc_data").val(datai);
	$(".ecc_escore").val(escore);
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + uri);

	modal.find('.modal-title').html('Alterar <strong>' + descricaoTitulo + '</strong> para...' );
});



$('#editarPesoModal').on('show.bs.modal', function(event) {
	var button = $(event.relatedTarget);
	
	
	var uri = button.data('uri');
	var id = button.data('id')
	var peso = button.data('peso')
	var status = button.data('status')
	var datainclusao = button.data('datainclusao')
	var datapesagem = button.data('datapesagem')
	var descricaoTitulo = button.data('descricao');

	//window.alert(peso);
	$(".peso_id").val(id);
	$(".peso_status").val(status);
	$(".peso_datainclusao").val(datainclusao);
	$(".peso_datapesagem").val(datapesagem);
	$(".peso_peso").val(peso);
	
	var modal = $(this);
	var form = modal.find('form');
	var action = form.data('url-base');
	if (!action.endsWith('/')) {
		action += '/';
	}
	form.attr('action', action + uri);

	modal.find('.modal-title').html('Alterar o peso <strong>' + descricaoTitulo + '</strong> para...' );
});




