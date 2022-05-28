window.onload = function() {
	let port = '';
	if (window.location.port !== '80' && window.location.port !== '443') {
		port = ':' + window.location.port;
	}

	window.ui = SwaggerUIBundle({
		url: `${window.location.protocol}//${window.location.hostname}${port}/docs/openapi.yaml`,
		dom_id: '#swagger-ui',
		presets: [
			SwaggerUIBundle.presets.apis,
			SwaggerUIStandalonePreset
		],
		layout: "StandaloneLayout"
	});
};
