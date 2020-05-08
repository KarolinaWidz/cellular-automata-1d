<html><head>
  <SCRIPT src="http://java.com/js/dtjava.js"></SCRIPT>
<script>
    function launchApplication(jnlpfile) {
        dtjava.launch(            {
                url : 'cellular_automaton_1d.jnlp'
            },
            {
                javafx : '8.0+'
            },
            {}
        );
        return false;
    }
</script>

<script>
    function javafxEmbedcellular_automaton_1d_id() {
        dtjava.embed(
            {
                id : 'cellular_automaton_1d_id',
                url : 'cellular_automaton_1d.jnlp',
                placeholder : 'javafx-app-placeholder',
                width : '600',
                height : '400'
            },
            {
                javafx : '8.0+'
            },
            {}
        );
    }
    <!-- Embed FX application into web page once page is loaded -->
    dtjava.addOnloadCallback(javafxEmbedcellular_automaton_1d_id);
</script>

</head><body>
<h2>Test page for <b>Cellular-Automaton</b></h2>
  <b>Webstart:</b> <a href='cellular_automaton_1d.jnlp' onclick="return launchApplication('cellular_automaton_1d.jnlp');">click to launch this app as webstart</a><br><hr><br>

  <!-- Applet will be inserted here -->
  <div id='javafx-app-placeholder'></div>
</body></html>
