package menu;


import javafx.animation.KeyFrame;
import javafx.animation.KeyValue;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.scene.image.*;
import javafx.util.Duration;
import map.*;
import entity.*;


public class Menu extends Application {
    private Pane change;
    private Player player;
    private Scene scene;
    private Monster monster1;
    private Monster monster2;
    private Monster monster3;
    private Monster monster4;
    private int countMana;
    private Text lastText = new Text();
    private Text otherText = new Text();

    @Override
    public void start(Stage stage) throws Exception {
        lastText.setFill(Color.WHITE);
        lastText.setScaleX(1);
        lastText.setScaleY(1);
        otherText.setFill(Color.WHITE);
        otherText.setScaleX(1);
        otherText.setScaleY(1);
        player = new Player(40,11,32, 32);
        stage.setTitle("RPG d'un Parfait Portugais");
        countMana = 0;
        Button readMe = new Button("Read me !");
        Button buttonP = new Button("Play");
        Button buttonCharacter = new Button("Choose your Caracter !!!");
        Button buttonE = new Button("Exit");
        setButtonPosition(buttonCharacter, 570, 350);
        setButtonPosition(buttonE, 610,650);
        setButtonPosition(buttonP, 570,260);
        setButtonPosition(readMe, 570,440);
        sizeButton(readMe, 150, 40);
        sizeButton(buttonP,150, 40);
        sizeButton(buttonE,70,30);
        sizeButton(buttonCharacter,150,40);
        addExitEvent(buttonE);
        Map map = new Map(20,20);
        map.setRandomlyMap();
        buttonP.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                change = new Pane();
                Button buttonRestart = new Button("Get a New map !");
                setButtonPosition(buttonRestart, 1100,650);
                buttonRestart.setMaxSize(150,40);
                buttonRestart.setMinSize(150,40);
                buttonRestart.setPrefSize(150,40);
                Button loadMap = new Button("Load your own map !");
                setButtonPosition(loadMap, 900,650);
                loadMap.setMaxSize(150,40);
                loadMap.setMinSize(150,40);
                loadMap.setPrefSize(150,40);
                monster1 = new Monster();
                monster2 = new Monster();
                monster3 = new Monster();
                monster4 = new Monster();
                change.getChildren().addAll(buttonRestart, loadMap);
                scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                    @Override
                    public void handle(KeyEvent keyEvent) {
                        if (keyEvent.getCode() == KeyCode.Q) {
                            player.moveLeft(map);
                            monster1.move(map, player);
                            monster2.move(map, player);
                            monster3.move(map, player);
                            monster4.move(map, player);
                            update(stage);
                        } else if(keyEvent.getCode() == KeyCode.D) {
                            player.moveRight(map);
                            monster1.move(map, player);
                            monster2.move(map, player);
                            monster3.move(map, player);
                            monster4.move(map, player);
                            update(stage);
                        } else if(keyEvent.getCode() == KeyCode.S) {
                            player.moveDown(map);
                            monster1.move(map, player);
                            monster2.move(map, player);
                            monster3.move(map, player);
                            monster4.move(map, player);
                            update(stage);
                        } else if(keyEvent.getCode() == KeyCode.Z) {
                            player.moveUp(map);
                            monster1.move(map, player);
                            monster2.move(map, player);
                            monster3.move(map, player);
                            monster4.move(map, player);
                            update(stage);
                        } else if(keyEvent.getCode() == KeyCode.SPACE) {
                            return;
                        }

                    }
                });
                buttonRestart.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        map.setRandomlyMap();
                        player.setLocationX(82);
                        player.setLocationY(82);
                        change = mapGet(map, player);
                        Button buttonRestart = new Button("Get a New map !");
                        setButtonPosition(buttonRestart, 1100,650);
                        buttonRestart.setMaxSize(150,40);
                        buttonRestart.setMinSize(150,40);
                        buttonRestart.setPrefSize(150,40);
                        change.getChildren().addAll(buttonRestart,loadMap);
                        buttonRestart.setOnAction(this::handle);
                        scene = new Scene(change, 1280,720);
                        scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());

                        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (keyEvent.getCode() == KeyCode.Q) {
                                    player.moveLeft(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.D) {
                                    player.moveRight(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.S) {
                                    player.moveDown(map);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.Z) {
                                    player.moveUp(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);

                                } else if(keyEvent.getCode() == KeyCode.E && player.getLevel() >= 5 && player.getMana() > 0) {
                                    attackAnim(stage, 64 , 0, "explosion", 0.3, 32);
                                    attackMonster(64,0);
                                    manaGestion();
                                    player.useMana(1);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.A && player.getLevel() >= 5 && player.getMana() > 0) {
                                    attackAnim(stage, -64 , 0, "explosion", 0.3, 32);
                                    attackMonster(-64,0);
                                    manaGestion();
                                    player.useMana(1);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.H && player.getLevel() >= 3 && player.getMana() > 0) {
                                    attackAnim(stage, -16 , -16, "heal", 0.5, 64);
                                    player.heal(10, 1);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.T && player.getLevel() >= 7 && player.getMana() > 1) {
                                    manaGestion();
                                    player.useMana(2);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    fireball(stage, -120,0,48);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.C) {
                                    attackAnim(stage, 32 , 0, "thunder", 0.5, 48);
                                    manaGestion();
                                    attackMonster(32,0);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.W) {
                                    attackAnim(stage, -32 , 0, "thunder", 0.5, 48);
                                    manaGestion();
                                    attackMonster(-32,0);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                }
                                if (checkReset()) {
                                    map.setRandomlyMap();
                                    change = mapGet(map, player);
                                    update(stage);
                                }

                            }
                        });
                        monster1.setDead(false);
                        monster2.setDead(false);
                        monster3.setDead(false);
                        monster4.setDead(false);
                        stage.setScene(scene);
                    }
                });
                loadMap.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        try {
                            map.readMap();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        change = mapGet(map, player);
                        Button loadMap = new Button("Load your own map !");
                        setButtonPosition(loadMap, 900,650);
                        loadMap.setMaxSize(150,40);
                        loadMap.setMinSize(150,40);
                        loadMap.setPrefSize(150,40);
                        change.getChildren().addAll(buttonRestart,loadMap);
                        loadMap.setOnAction(this::handle);
                        player = new Player(60,10,32, 32);
                        scene = new Scene(change, 1280,720);
                        scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
                        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
                            @Override
                            public void handle(KeyEvent keyEvent) {
                                if (keyEvent.getCode() == KeyCode.Q) {
                                    player.moveLeft(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.D) {
                                    player.moveRight(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.S) {
                                    player.moveDown(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.Z) {
                                    player.moveUp(map);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);

                                } else if(keyEvent.getCode() == KeyCode.E && player.getLevel() >=5 && player.getMana() > 0) {
                                    attackAnim(stage, 64, 0, "explosion", 0.3, 32);
                                    attackMonster(64,0);
                                    manaGestion();
                                    player.useMana(1);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.A && player.getLevel() >=5 && player.getMana() > 0) {
                                    attackAnim(stage, -64 , 0, "explosion", 0.3, 32);
                                    attackMonster(-64,0);
                                    manaGestion();
                                    player.useMana(1);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.H && player.getLevel() >= 3 && player.getMana() > 0) {
                                    attackAnim(stage, -16 , -16, "heal", 1, 64);
                                    player.heal(10, 1);
                                    manaGestion();
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                }else if(keyEvent.getCode() == KeyCode.T && player.getLevel() >= 7 && player.getMana() > 1) {
                                    manaGestion();
                                    player.useMana(2);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    fireball(stage, -120,0,48);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.W) {
                                    attackAnim(stage, 32 , 0, "thunder", 0.5, 48);
                                    manaGestion();
                                    attackMonster(32,0);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                } else if(keyEvent.getCode() == KeyCode.C) {
                                    attackAnim(stage, -32 , 0, "thunder", 0.5, 48);
                                    manaGestion();
                                    attackMonster(-32,0);
                                    monster1.move(map, player);
                                    monster2.move(map, player);
                                    monster3.move(map, player);
                                    monster4.move(map, player);
                                    update(stage);
                                }
                                if (checkReset()) {
                                    map.setRandomlyMap();
                                    change = mapGet(map, player);
                                    update(stage);
                                }
                            }
                        });
                        monster1.setDead(false);
                        monster2.setDead(false);
                        monster3.setDead(false);
                        monster4.setDead(false);
                        stage.setScene(scene);
                    }
                });
                change.setId("game");
                Scene scene = new Scene(change, 1280,720);
                scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
                stage.setScene(scene);
            }
        });
        buttonCharacter.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pane character = new Pane();
                Button perso1 = new Button("Select");
                perso1 = setButton(perso1, 50, 50,115,20);
                ImageView image1 = new ImageView(new Image(("menu/image/perso2.gif")));
                image1 = setImage(image1, 256, 20,50);
                setButtonPosition(buttonP, 570,650);
                perso1.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/perso2.gif", 48);
                    }
                });
                Button perso2 = new Button("Select");
                perso2 = setButton(perso2, 50, 50,250,20);
                ImageView image2 = new ImageView(new Image(("menu/image/jean.gif")));
                image2 = setImage(image2, 150, 200,90);
                perso2.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/jean.gif", 32);
                    }
                });
                Button perso3 = new Button("Select");
                perso3 = setButton(perso3, 50, 50,390,20);
                ImageView image3 = new ImageView(new Image(("menu/image/perso3.gif")));
                image3 = setImage(image3, 180, 320,70);
                perso3.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/perso3.gif", 32);
                    }
                });
                Button perso4 = new Button("Select");
                perso4 = setButton(perso4, 50, 50,530,20);
                ImageView image4 = new ImageView(new Image(("menu/image/perso5.gif")));
                image4 = setImage(image4, 180, 460,50);
                perso4.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/perso5.gif", 32);
                    }
                });
                Button perso5 = new Button("Select");
                perso5 = setButton(perso5, 50, 50,670,20);
                ImageView image5 = new ImageView(new Image(("menu/image/mage.gif")));
                image5 = setImage(image5, 150, 610,70);
                perso5.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/mage.gif", 32);
                    }
                });
                Button perso6 = new Button("Select");
                perso6 = setButton(perso6, 50, 50,800,20);
                ImageView image6 = new ImageView(new Image(("menu/image/genji.gif")));
                image6 = setImage(image6, 220, 680,50);
                perso6.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/genji.gif", 48);
                    }
                });
                Button perso7 = new Button("Select");
                perso7 = setButton(perso7, 50, 50,910,20);
                ImageView image7 = new ImageView(new Image(("menu/image/knight.gif")));
                image7 = setImage(image7, 150, 850,80);
                perso7.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/knight.gif", 32);
                    }
                });
                Button perso8 = new Button("Select");
                perso8 = setButton(perso8, 50, 50,1010,20);
                ImageView image8 = new ImageView(new Image(("menu/image/batknight.gif")));
                image8 = setImage(image8, 150, 980,70);
                perso8.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/batknight.gif", 32);
                    }
                });
                Button perso9 = new Button("Select");
                perso9 = setButton(perso9, 50, 50,1100,20);
                ImageView image9 = new ImageView(new Image(("menu/image/mage2.gif")));
                image9 = setImage(image9, 180, 1040,90);
                perso9.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        player.setImage("menu/image/mage2.gif", 48);
                    }
                });
                character.setId("choose");
                character.getChildren().addAll(buttonP,perso1,image1,perso2,image2,perso3,image3,perso4,image4,perso5,image5,perso6,image6,perso7,image7,perso8,image8,perso9,image9);
                scene = new Scene(character, 1280,720);
                scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
                stage.setScene(scene);
                showStage(stage);
            }
        });
        readMe.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Pane readPane = new Pane();
                setButtonPosition(buttonP, 570, 600);
                setButtonPosition(buttonCharacter,570 , 650);
                readPane.getChildren().addAll(buttonP, buttonCharacter);
                Text read = new Text();
                read.setText("Your goal is to get to the level 10 !  Once you do this you have won !" + '\n' +
                        "If you want to create your own map you ahave to respect a few rules." + '\n' +
                        "First you will have four differents aspect... The Wall, the Ground, the Lava and the Floor" + '\n' +
                        "The wall is an # in your map, the ground is a defalut char, the Lava is a L and the floor is a *" + '\n' +
                        "The last thing you have to know is that the map can only be a 20x20" + '\n' +
                        "Enjoy your game !!!");
                read.setFill(Color.WHITE);
                read.setScaleX(2);
                read.setScaleY(2);
                read.setX(500);
                read.setY(350);
                readPane.getChildren().add(read);
                readPane.setId("read");
                scene = new Scene(readPane, 1280,720);
                scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
                stage.setScene(scene);
                showStage(stage);
            }

        });
        Pane layout = new Pane();
        layout.getChildren().addAll(buttonP,buttonCharacter,buttonE, readMe);
        layout.setId("pane");
        scene = new Scene(layout, 1280,720);
        scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
        stage.setScene(scene);
        showStage(stage);
    }

    public void showStage(Stage stage) {
        stage.show();
    }

    public Button setButtonPosition(Button button, int x, int y) {
        button.setLayoutX(x);
        button.setLayoutY(y);
        return button;
    }

    public Button addExitEvent(Button button) {
        button.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                System.exit(0);
            }
        });
        return button;
    }

    public Button sizeButton(Button button,int width, int height) {
        button.setMaxSize(width,height);
        button.setMinSize(width,height);
        button.setPrefSize(width,height);
        return button;
    }

    public Pane mapGet(Map map, Player player) {
        Pane layout = new Pane();
        int sizeMap = map.getMap().length;
        int x = 50;
        int xbase = x;
        int y = 50;
        int sizeImg = 32;
        // https://i.pinimg.com/564x/0f/c4/ce/0fc4ce53dcd57e0c0b3b8b3888503a43--d-texture-paint-texture.jpg
        for(int i = 0; i < sizeMap; i++) {
            for (int j = 0; j < sizeMap; j++) {
                //if((i == 0 || i == sizeMap  - 1) || (j == 0 || j == sizeMap  - 1)) {
                if (map.getMap()[i][j] instanceof Wall) {
                    ImageView image = new ImageView(new Image(("menu/image/Wall.jpg")));
                    image.setFitHeight(sizeImg);
                    image.setFitWidth(sizeImg);
                    image.setLayoutX(x);
                    image.setLayoutY(y);
                    layout.getChildren().add(image);
                } if (map.getMap()[i][j] instanceof Ground) {
                    ImageView image = new ImageView(new Image(("menu/image/ground.jpg")));
                    image.setFitHeight(sizeImg);
                    image.setFitWidth(sizeImg);
                    image.setLayoutX(x);
                    image.setLayoutY(y);
                    layout.getChildren().add(image);
                } if (map.getMap()[i][j] instanceof Lava) {
                    ImageView image = new ImageView(new Image(("menu/image/lava.jpg")));
                    image.setFitHeight(sizeImg);
                    image.setFitWidth(sizeImg);
                    image.setLayoutX(x);
                    image.setLayoutY(y);
                    layout.getChildren().add(image);
                } if (map.getMap()[i][j] instanceof Sol) {
                    ImageView image = new ImageView(new Image(("menu/image/sol.jpg")));
                    image.setFitHeight(sizeImg);
                    image.setFitWidth(sizeImg);
                    image.setLayoutX(x);
                    image.setLayoutY(y);
                    layout.getChildren().add(image);
                }
                if (x < 700) {
                    x = x + sizeImg;
                }
            }
            if (y < 700) {
                y = y + sizeImg;
            }
            x = xbase;

        }
        layout.setId("game");
        return layout;
    }

    public void update(Stage stage) {
        Button exit = new Button("Exit Game");
        exit = sizeButton(exit, 100, 40);
        exit = setButtonPosition(exit,750,650);
        addExitEvent(exit);
        Button hp = new Button("Hp : " + Integer.toString(player.getHp()));
        hp = sizeButton(hp, 250, 60);
        hp = setButtonPosition(hp,1000,100);
        Button xp = new Button("Xp : " + Integer.toString(player.getXp()) + "/" + Integer.toString(player.getXpGoal()));
        xp = sizeButton(xp, 250, 60);
        xp = setButtonPosition(xp,1000,200);
        Button level = new Button("Level : " + Integer.toString(player.getLevel()));
        level = sizeButton(level, 250, 60);
        level = setButtonPosition(level,750,200);
        Button mana = new Button("Mana : " + Integer.toString(player.getMana()) + "/" + Integer.toString(player.getManaMax()));
        mana = sizeButton(mana, 250, 60);
        mana = setButtonPosition(mana,750,100);
        if(player.getHp() <= 0) {
            Pane loose = new Pane();
            loose.setId("loose");
            scene = new Scene(loose, 1280,720);
            scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
            stage.setScene(scene);
            showStage(stage);
        }
        if(player.getLevel() >= 10) {
            Pane win = new Pane();
            win.setId("win");
            scene = new Scene(win, 1280,720);
            scene.getStylesheets().addAll(this.getClass().getResource("image/style.css").toExternalForm());
            stage.setScene(scene);
            showStage(stage);
        }
        change.getChildren().removeAll(player.getImage() , monster1.getImage(),monster2.getImage(),monster3.getImage(),monster4.getImage(), hp,xp, level, mana,exit);
        change.getChildren().addAll(player.getImage() , monster1.getImage(),monster2.getImage(),monster3.getImage(),monster4.getImage(), hp,xp, level, mana,exit);
        verifyMonster();
        stage.setScene(scene);
        stage.show();
    }

    public void verifyMonster() {
        if (monster1.getDead()) {
            change.getChildren().removeAll(monster1.getImage());
        } if (monster2.getDead()) {
            change.getChildren().removeAll(monster2.getImage());
        } if (monster3.getDead()) {
            change.getChildren().removeAll(monster3.getImage());
        } if (monster4.getDead()) {
            change.getChildren().removeAll(monster4.getImage());
        }
    }
    public void attackAnim(Stage stage, int x , int y, String type, double duration, int size) {
        ImageView image = null;
        if (type == "explosion") {
            image = new ImageView(new Image(("menu/image/explosion.gif")));
        } else
        if (type == "heal") {
            image = new ImageView(new Image(("menu/image/heal.gif")));
        } else if (type == "thunder") {
            image = new ImageView(new Image(("menu/image/thunder.gif")));
        }
        image.setFitHeight(size);
        image.setFitWidth(size);
        image.setLayoutX(player.getPositionX() + x);
        image.setLayoutY(player.getPositionY() + y);
        change.getChildren().remove(player.getImage());
        change.getChildren().addAll(player.getImage(), image);
        stage.setScene(scene);
        stage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(duration));
        ImageView finalImage = image;
        pause.setOnFinished(event -> {
            clearAtk(finalImage, stage);
        });
        pause.play();

    }

    public void clearAtk(ImageView image, Stage stage) {
        change.getChildren().remove(image);
        verifyMonster();
        stage.setScene(scene);
        stage.show();
    }

    public void attackMonster(int x, int y) {
        if(Math.abs(monster1.getPositionX() - (player.getPositionX() + x)) < 32 && Math.abs(monster1.getPositionY() - player.getPositionY() + y) < 32) {
            monster1.takeDmg(player ,player.getAttack());
            if(player.levelUp()) {
                change.getChildren().remove(otherText);
                otherText.setText("You won a level");
                otherText.setX(800);
                otherText.setY(400);
                change.getChildren().add(otherText);
                checkLevel();
            }
        }
        if(Math.abs(monster2.getPositionX() - (player.getPositionX() + x)) < 32 && Math.abs(monster2.getPositionY() - player.getPositionY() + y) < 32) {
            monster2.takeDmg(player ,player.getAttack());
            if(player.levelUp()) {
                change.getChildren().removeAll(otherText,lastText);
                otherText.setText("You won a level");
                otherText.setX(800);
                otherText.setY(400);
                change.getChildren().add(otherText);
                checkLevel();
            }
        }
        if(Math.abs(monster3.getPositionX() - (player.getPositionX() + x)) < 32 && Math.abs(monster3.getPositionY() - player.getPositionY() + y) < 32) {
            monster3.takeDmg(player,player.getAttack());
            if(player.levelUp()) {
                change.getChildren().removeAll(otherText,lastText);
                otherText.setText("You won a level");
                otherText.setX(800);
                otherText.setY(400);
                change.getChildren().add(otherText);
                checkLevel();
            }
        }
        if(Math.abs(monster4.getPositionX() - (player.getPositionX() + x)) < 32 && Math.abs(monster4.getPositionY() - player.getPositionY() + y) < 32) {
            monster4.takeDmg(player ,player.getAttack());
            if(player.levelUp()) {
                change.getChildren().removeAll(otherText,lastText);
                otherText.setText("You won a level");
                otherText.setX(800);
                otherText.setY(400);
                change.getChildren().add(otherText);
                checkLevel();
            }
        }
    }

    public void manaGestion() {
        countMana ++;
        if(countMana >= 8) {
            countMana = 0;
            player.manaRegen();

        }
    }

    public void fireball(Stage stage,int x, int y, int size) {
        ImageView image = new ImageView(new Image(("menu/image/fireball.gif")));
        image.setFitHeight(size);
        image.setFitWidth(size);
        image.setLayoutX(player.getPositionX() + x);
        image.setLayoutY(player.getPositionY() + y);
        Timeline timeline = new Timeline();
        timeline.getKeyFrames().addAll(
                new KeyFrame(Duration.ZERO, new KeyValue(image.translateXProperty(), player.getPositionX())),
                new KeyFrame(new Duration(50), new KeyValue(image.translateXProperty(), player.getPositionX() + 400))

        );
        timeline.setOnFinished(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                fireballAttack();
            }
        });
        timeline.play();
        change.getChildren().add(image);
        stage.setScene(scene);
        stage.show();
        PauseTransition pause = new PauseTransition(Duration.seconds(1.5));
        pause.setOnFinished(event -> {
            clearAtk(image, stage);
        });
        pause.play();
    }

    public void fireballAttack() {
        if(Math.abs(player.getPositionY() - monster1.getPositionY()) < 32 && player.getPositionX() < monster1.getPositionX()) {
            monster1.takeDmg(player, player.getAttack());
        } else if(Math.abs(player.getPositionY() - monster2.getPositionY()) < 32 && player.getPositionX() < monster2.getPositionX()) {
            monster2.takeDmg(player, player.getAttack());
        } else if(Math.abs(player.getPositionY() - monster3.getPositionY()) < 32 && player.getPositionX() < monster3.getPositionX()) {
            monster3.takeDmg(player, player.getAttack());
        } else if(Math.abs(player.getPositionY() - monster4.getPositionY()) < 32 && player.getPositionX() < monster4.getPositionX()) {
            monster4.takeDmg(player, player.getAttack());
        }
    }

    public boolean checkReset() {
        if(monster1.getDead()) {
            if(monster2.getDead()) {
                if(monster3.getDead()) {
                    if(monster4.getDead()) {
                        monster1.setDead(false);
                        monster1.setHp(10);
                        monster2.setDead(false);
                        monster2.setHp(10);
                        monster3.setDead(false);
                        monster3.setHp(10);
                        monster4.setDead(false);
                        monster4.setHp(10);
                    }
                }
            }
        }
        return false;
    }
    public Button setButton(Button button, int width, int height, int x , int y) {
        setButtonPosition(button, x,y);
        sizeButton(button,width, height);
        return button;
    }
    public ImageView setImage(ImageView image, int size , int x , int y) {
        image.setFitHeight(size);
        image.setFitWidth(size);
        image.setLayoutX(x);
        image.setLayoutY(y);
        return image;
    }

    public void checkLevel() {
        if(player.getLevel() == 3) {
            change.getChildren().remove(lastText);
            lastText.setText("You have a new spell press H to heal yourself !!");
            lastText.setX(800);
            lastText.setY(500);
            change.getChildren().add(lastText);
        }
        if(player.getLevel() == 5) {
            change.getChildren().remove(lastText);
            lastText.setText("You have a new spell press A or E to explode your enemies !!");
            lastText.setX(800);
            lastText.setY(500);
            change.getChildren().add(lastText);
        }
        if(player.getLevel() == 7) {
            change.getChildren().remove(lastText);
            lastText.setText("You have a new spell press T to throw FIREBAAAAAAAALLS !!");
            lastText.setX(800);
            lastText.setY(500);
            change.getChildren().add(lastText);
        }
    }
}
