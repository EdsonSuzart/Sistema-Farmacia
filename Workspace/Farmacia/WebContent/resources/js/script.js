function verificar(xhr, status, args, dl, tb) {
	if (args.validationFailed) {
		PF(dl).jq.effect("shake", {
			times : 5
		}, 100);
	} else {
		PF(dl).hide();
		PF(tb).clearFilters();

	}
}

// FUNÇÃO PARA TREMER A CAIXA DE DIÁLOGO
