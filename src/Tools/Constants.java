package Tools;

public class Constants {

    /////////////////////database queries///////////////////////
    //Creates
    public static final String create_database_query = "create database if not exists stl";
    public static final String create_conditions_table_query = "create table if not exists stl.Conditions (\n" +
            "    `condition_id` int not null auto_increment,\n" +
            "    `condition_name` varchar(20) not null,\n" +
            "    `Date` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,\n" +
            "    `init_total_time` double,\n" +
            "    `better_total_time` double,\n" +
            "    `distribution_path` varchar(255),\n" +
            "    `init_awt` double,\n" +
            "    `better_awt` double,\n" +
            "    `phase_time` double,\n" +
            "    PRIMARY KEY (`condition_id`)\n" +
            ")";
    public static final String create_directionsInfo_table_query = "create table if not exists stl.DirectionsInfo (\n" +
            "    `direction_info_id` int not null auto_increment, \n" +
            "    `cars_amount` int not null,\n" +
            "    `average_speed` int not null,\n" +
            "    `limit_speed` int not null,\n" +
            "    `type` varchar(20) not null,\n" +
            "    PRIMARY KEY (`direction_info_id`)\n" +
            ")";
    public static final String create_traffic_lights_table_query = "create table if not exists stl.TrafficLights (\n" +
            "    `traffic_light_id` int not null auto_increment,\n" +
            "    `actual_state` int not null,\n" +
            "    primary key(`traffic_light_id`)\n" +
            ")";
    public static final String create_cars_table_query = "create table if not exists stl.Cars (\n" +
            "    `car_id` int not null auto_increment,\n" +
            "    `direction_info_id` int not null,\n" +
            "    `car_type` varchar(25),\n" +
            "    `length` int not null,\n" +
            "    `speed` int not null,\n" +
            "    primary key(`car_id`),\n" +
            "    foreign key(`direction_info_id`) references DirectionsInfo(`direction_info_id`)\n" +
            ")";
    public static final String create_crossroadsInfo_table_query = "create table if not exists stl.CrossroadsInfo(\n" +
            "    `crossroad_info_id` int not null auto_increment,\n" +
            "    `condition_id` int not null,\n" +
            "    `north_direction_info_id` int,\n" +
            "    `east_direction_info_id` int,\n" +
            "    `south_direction_info_id` int,\n" +
            "    `west_direction_info_id` int,\n" +
            "    PRIMARY KEY (`crossroad_info_id`),\n" +
            "    foreign key(`condition_id`) references Conditions(`condition_id`),\n" +
            "    foreign key(`north_direction_info_id`) references DirectionsInfo(`direction_info_id`),\n" +
            "    foreign key(`east_direction_info_id`) references DirectionsInfo(`direction_info_id`),\n" +
            "    foreign key(`south_direction_info_id`) references DirectionsInfo(`direction_info_id`),\n" +
            "    foreign key(`west_direction_info_id`) references DirectionsInfo(`direction_info_id`)\n" +
            ")";
    public static final String create_crossroads_table_query = "create table if not exists stl.Crossroads(\n" +
            "    `crossroad_id` int not null auto_increment,\n" +
            "    `crossroad_info_id` int not null,\n" +
            "    `north_traffic_light_id` int,\n" +
            "    `east_traffic_light_id` int,\n" +
            "    `south_traffic_light_id` int,\n" +
            "    `west_traffic_light_id` int,\n" +
            "    `actual_state` int not null,\n" +
            "    primary key(`crossroad_id`),\n" +
            "    foreign key(`crossroad_info_id`) references CrossroadsInfo(`crossroad_info_id`),\n" +
            "    foreign key(`north_traffic_light_id`) references TrafficLights(`traffic_light_id`),\n" +
            "    foreign key(`east_traffic_light_id`) references TrafficLights(`traffic_light_id`),\n" +
            "    foreign key(`south_traffic_light_id`) references TrafficLights(`traffic_light_id`),\n" +
            "    foreign key(`west_traffic_light_id`) references TrafficLights(`traffic_light_id`)\n" +
            ")";

    //Drops
    public static final String drop_database_query = "drop database if exists stl";

    //Saves
    public static final String insert_conditions_statment = "insert into stl.Conditions(condition_name,init_total_time,better_total_time,distribution_path,init_awt,better_awt,phase_time) values(?,?,?,?,?,?,?)";
    public static final String insert_directionsInfo_statment = "insert into "
            + "stl.DirectionsInfo(cars_amount, average_speed, limit_speed, type)"
            + " values(?,?,?,?)";
    public static final String insert_crossroadsInfo_statment = "insert into "
            + "stl.CrossroadsInfo(condition_id, north_direction_info_id, east_direction_info_id, south_direction_info_id, west_direction_info_id)"
            + " values(?,?,?,?,?)";
    public static final String insert_crossroads_statment = "insert into"
            + " stl.crossroads(crossroad_info_id, north_traffic_light_id, east_traffic_light_id, south_traffic_light_id, west_traffic_light_id, actual_state)"
            + " values(?,?,?,?,?,?)";
    public static final String insert_traffic_light = "insert into stl.trafficlights(actual_state) values(?)";

    //Selects
    public static final String select_0_condition_query = "select * from stl.conditions limit 0";
    public static final String select_conditions_dates_query = "select Date from stl.conditions";
    public static final String conditions_dates = "Date";
    public static final String select_condition_by_date_query = "select * from stl.conditions where date =?";
    public static final String select_crossroadsInfo_ids_query = "select crossroad_info_id from stl.crossroadsinfo where condition_id =?";
    public static final String select_crossroadsInfo_query = "select north_direction_info_id, east_direction_info_id, south_direction_info_id, west_direction_info_id"
            + " from stl.crossroadsinfo where crossroad_info_id =?";
    public static final String select_directionInfo_query = "select cars_amount, average_speed, limit_speed from stl.directionsinfo "
            + "where direction_info_id = ?";
    ///////////////////////////////////////////////////////////

    //log messages
    public static final String connection_fail = "ERREUR : échec de la connexion !";
    public static final String create_database_fail = "ERREUR : échec de la création de la base de données locale !";

    //window labels
    public static final String traffic_conditions_window_label = "Choisissez les conditions de circulation";
    public static final String home_page_window_label = "Bienvenue au Smart Traffic Light !";
    public static final String client_type_window_label = "Choisissez votre occupation";
    public static final String simulation_window_label = "Simulation";
    public static final String about_us_window_label = "À propos de nous";
    public static final String database_window_label = "Base de données";
    public static final String database_connect_window_label = "Connectez-vous à la base de données";
    public static final String random_window_label = "Aléatoire";
    public static final String go_to_previous_page_window_label = "Retourner à la fenêtre précédente";
    public static final String exit_window_label = "Sortie";
    public static final String fail_window_label = "Échec";

    //buttons labels
    public static final String yes_button_label = "Oui";
    public static final String no_button_label = "Non";
    public static final String reset_button_label = "Réinitialiser";
    public static final String run_button_label = "Exécuter";
    public static final String analyst_button_label = "Analyste";
    public static final String observer_button_label = "Observateur";
    public static final String save_button_label = "Enregistrer";
    public static final String back_button_label = "Retour";
    public static final String start_button_label = "Démarrer";
    public static final String database_button_label = "Base de données";
    public static final String random_button_label = "Aléatoire";
    public static final String info_button_label = "Info";
    public static final String lets_start_button_label = "Commençons";
    public static final String about_us_button_label = "À propos de nous";
    public static final String close_button_label = "Fermer";
    public static final String cancel_button = "Annuler";
    public static final String confirm_button_database = "Choisir";
    public static final String open_csv_button_label = "Ouvrir CSV";

    //pages labels
    public static final String about_us_text = "Smart Traffic Light\nVersion 1.0\n" +
            "Créé par le Groupe 2, Exars 2025";
    public static final String go_to_previous_page_from_conditions_text = "Si vous revenez en arrière, toutes les options seront réinitialisées\n" +
            "Êtes-vous sûr de vouloir revenir en arrière ?";
    public static final String go_to_previous_page_from_simulation_text = "La simulation en cours sera supprimée\n" +
            "Êtes-vous sûr de vouloir revenir en arrière ?";
    public static final String crossroad_1_label = "Carrefour 1";
    public static final String crossroad_2_label = "Carrefour 2";
    public static final String other_features_label = "Autres fonctionnalités";
    public static final String route_label = "Itinéraire";
    public static final String east_label = "Est";
    public static final String west_label = "Ouest";
    public static final String north_label = "Nord";
    public static final String south_label = "Sud";
    public static final String cars_count_label = "Nombre de voitures";
    public static final String actual_speed_label = "Vitesse actuelle";
    public static final String speed_limit_label = "Limite de vitesse";
    public static final String window_label = "Smart Traffic Light";

    public static final String url_label = "url : ";
    public static final String user_label = "utilisateur : ";
    public static final String password_label = "mot de passe : ";
    public static final String connect_button = "Connecter";
    public static final String create_database_button = "Créer une base de données";

    public static final String choose_file_from_database_label = "Choisissez le fichier des conditions";
    public static final String generate_random_data_label = "Générer des données aléatoires ?";
    public static final String reset_conditions_label = "Réinitialiser toutes les valeurs ?";
    public static final String exit_text_label = "Êtes-vous sûr de vouloir quitter ?";
    public static final String fail_text_label = "L'observateur n'a pas ces permissions.";
    public static final String csv_fail_text_label = "Le fichier ouvert a un format de données incorrect.";

    public static final double POLICE_MAX_SPEED = 70;
    public static final double AMBULANCE_MAX_SPEED = 70;
    public static final double TAXI_MAX_SPEED = 70;
    public static final double USUAL_MAX_SPEED = 70;
    public static final double TRACK_MAX_SPEED = 70;

    //sizes
    public static final double TRAFFIC_LIGHT_HEIGHT = 40;
    public static final double TRAFFIC_LIGHT_WIDTH = 15;

    //times
    public static final int TRAFFIC_LIGHT_CHANGING_TIME = 2;
    public static final int TRAFFIC_LIGHT_MIN_DISTRIBUTION = 5;
    public static final double TRAFFIC_LIGHT_PHASE_TIME = 20;

    //  directions
    public static final int NORTH_DIRECTION = 0;
    public static final int EAST_DIRECTION = 1;
    public static final int SOUTH_DIRECTION = 2;
    public static final int WEST_DIRECTION = 3;
    public static final int NORTH_EAST_DIRECTION = 4;
    public static final int SOUTH_EAST_DIRECTION = 5;
    public static final int SOUTH_WEST_DIRECTION = 6;
    public static final int NORTH_WEST_DIRECTION = 7;
    public static final int EAST_NORTH_DIRECTION = 8;
    public static final int WEST_NORTH_DIRECTION = 9;
    public static final int WEST_SOUTH_DIRECTION = 10;
    public static final int EAST_SOUTH_DIRECTION = 11;
    public static final int EAST_WEST_DIRECTION = 12;
    public static final int WEST_EAST_DIRECTION = 13;

    //directions names
    public static final String DIRECTION_NAME_EAST_WEST = "est-ouest";
    public static final String DIRECTION_NAME_NORTH_SOUTH = "nord-sud";
    public static final String DIRECTION_NAME_WEST_EAST = "ouest-est";
    public static final String DIRECTION_NAME_SOUTH_NORTH = "sud-nord";
    public static final String DIRECTION_NAME_EAST_NORTH = "est-nord";
    public static final String DIRECTION_NAME_WEST_SOUTH = "ouest-sud";
    public static final String DIRECTION_NAME_NORTH_EAST = "nord-est";
    public static final String DIRECTION_NAME_SOUTH_WEST = "sud-ouest";
    public static final String DIRECTION_NAME_EAST_SOUTH = "est-sud";
    public static final String DIRECTION_NAME_WEST_NORTH = "ouest-nord";
    public static final String DIRECTION_NAME_NORTH_WEST = "nord-ouest";
    public static final String DIRECTION_NAME_SOUTH_EAST = "sud-est";

    public static final String DIRECTION_NAME_EAST = "est";
    public static final String DIRECTION_NAME_WEST = "ouest";
    public static final String DIRECTION_NAME_NORTH = "nord";
    public static final String DIRECTION_NAME_SOUTH = "sud";
    public static final String CROSSROAD_NAME_FIRST = "premier";
    public static final String CROSSROAD_NAME_SECOND = "deuxième";

    //Units
    public static final double METER_TO_PIXEL = 15;

    //String delimiters
    public static final String PHASE_DELIMITER = "->";
    public static final String TIMES_DELIMITER = ":";

    //Start values
    public static final int CARS_COUNT_SHORT_ROAD_DEFAULT = 3;
    public static final int CARS_COUNT_LONG_ROAD_DEFAULT = 10;
    public static final int CARS_COUNT_LONG_ROAD_MAX = 100;
    public static final int CARS_COUNT_SHORT_ROAD_MAX = 4;
    public static final int CARS_COUNT_MIN = 1;

    public static final int SPEED_LIMIT_DEFAULT = 50;
    public static final int SPEED_LIMIT_MAX = 110;
    public static final int SPEED_LIMIT_MIN = 50;

    public static final int ACTUAL_LIMIT_DEFAULT = 50;
    public static final int ACTUAL_LIMIT_MAX = 110;
    public static final int ACTUAL_LIMIT_MIN = 30;

    public static final double SAFETY_DISTANCE = 2;
    public static final double SAFETY_DISTANCE_TO_START = 3;

    public static final double SAFETY_DISTANCE_TO_STOP = 1;
    public static final double START_DISTANCE_BETWEEN_CARS = 2;

    //info text
    public static final String INFO_LABEL = "Informations principales";
    public static final String INFO_DIRECTIONS_LABEL = "Informations sur les directions";
    public static final String INFO_TRAFFIC_LIGHTS_LABEL = "Informations sur les feux de circulation";
    public static final String INFO_CARS_COUNT_LABEL = "Informations sur le nombre de voitures";
    public static final String INFO_SPEED_LIMIT_LABEL = "Informations sur la limite de vitesse";
    public static final String INFO_ACTUAL_SPEED_LABEL = "Informations sur la vitesse actuelle";

    public static final String INFO_TEXT = "Avant de démarrer l'application, vous devez sélectionner les conditions que l'algorithme essaiera de résoudre." +
            "\n\nLes conditions incluent les voitures et la vitesse pour deux intersections." +
            "\n\nL'utilisateur peut définir ces données manuellement, les charger à partir de la base de données en cliquant sur le bouton 'Base de données', ou les sélectionner de manière aléatoire en cliquant sur le bouton 'Aléatoire'." +
            "\n\nPour réinitialiser tous les paramètres sélectionnés, appuyez sur le bouton 'Réinitialiser'.";
    public static final String INFO_DIRECTIONS_TEXT = "Chaque intersection a quatre routes." +
            "\n\nPour une meilleure compréhension du mouvement du véhicule, l'image à gauche montre l'ordre avec les noms des côtés et c'est dans cet ordre que les voies et les directions de mouvement seront appelées.";
    public static final String INFO_TRAFFIC_LIGHTS_TEXT = "Chaque voie est régulée par un feu de circulation. Dans la figure, la flèche du feu de circulation indique la voie qui dépendra du feu de circulation particulier." +
            "\n\nChaque feu de circulation a 4 états : vert, jaune, rouge, rouge-jaune." +
            "\nVert - circulation autorisée." +
            "\nJaune - arrêt du mouvement." +
            "\nRouge - les voitures attendent le vert." +
            "\nRouge-jaune - les voitures sont prêtes à partir." +
            "\n\nIl faut 3 secondes pour changer l'état des couleurs du feu de circulation.";
    public static final String INFO_CARS_COUNT_TEXT = "Le nombre de voitures indique combien de voitures doivent traverser l'intersection depuis le côté sélectionné.";
    public static final String INFO_SPEED_LIMIT_TEXT = "La limite de vitesse indique la limite de vitesse pour la route sélectionnée.";
    public static final String INFO_ACTUAL_SPEED_TEXT = "La vitesse actuelle indique la vitesse à laquelle le mouvement se déroule en temps réel." +
            "\n\nLa vitesse actuelle peut être inférieure à la limite de vitesse, mais ne peut pas être supérieure à la limite de vitesse sur la route.";
}