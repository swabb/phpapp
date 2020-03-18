<!-- mysqli -->
<?php
    $my_host     = $_ENV["DB_HOST"];
    $my_user     = $_ENV["DB_USER"];
    $my_password = $_ENV["DB_PASS"];
    $my_dbport   = $_ENV["DB_PORT"];
    $my_db_name  = $_ENV["DB_NAME"];
    $link = mysqli_connect( $my_host, $my_user, $my_password, $my_db_name);

    if (!$link) {
        echo "<p><h1>Not OK.</h1></p>" . PHP_EOL;
        echo "<p><b>Errno code:</b> " . mysqli_connect_errno() . PHP_EOL . "</p>";
        echo "<p><b>Error text:</b> " . mysqli_connect_error() . PHP_EOL . "</p>";
        echo "<p><b>Host:</b> $my_host" . PHP_EOL . "</p>";
        exit;
    }

    echo "<p><H1>OK Connection.</h1>" . PHP_EOL . "</p>";
    echo "<p><b>Server info:</b> " . mysqli_get_host_info($link) . PHP_EOL . "</p>";

    mysqli_close($link);
?>
