<html><head>
  <SCRIPT src="https://java.com/js/dtjava.js"></SCRIPT>
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
<h2>You can try Cellular-Automaton here:</h2>
  <b>If you don't see application below:</b> <a href='cellular_automaton_1d.jnlp' onclick="return launchApplication('cellular_automaton_1d.jnlp');">click here to download this app</a><br><hr><br>

  <!-- Applet will be inserted here -->
  <div id='javafx-app-placeholder'></div>
</body></html>
