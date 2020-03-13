<!-- mysqli -->
<?php
    $my_host     = $_ENV["DB_HOST"];
    $my_user     = $_ENV["DB_USER"];
    $my_password = $_ENV["DB_PASS"];
    $my_dbport   = $_ENV["DB_PORT"];
    $my_db_name  = $_ENV["DB_NAME"];
    $link = mysqli_connect( $my_host, $my_user, $my_password, $my_db_name);

    if (!$link) {
        echo "Not OK." . PHP_EOL;
        echo "Errno code: " . mysqli_connect_errno() . PHP_EOL;
        echo "Error text: " . mysqli_connect_error() . PHP_EOL;
        echo "Host: $my_host" . PHP_EOL;
        exit;
    }

    echo "OK Connection." . PHP_EOL;
    echo "Server info: " . mysqli_get_host_info($link) . PHP_EOL;

    mysqli_close($link);
?>
