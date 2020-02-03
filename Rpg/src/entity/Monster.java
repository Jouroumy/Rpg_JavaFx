package entity;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import map.Map;
import map.Wall;

public class Monster {
    public int Hp;
    public int attack;
    public int positionX;
    public int positionY;
    public int speed;
    public ImageView image;
    public boolean dead;

    public Monster() {
        this.Hp = 10;
        this.attack = 5;
        this.speed = 32;
        this.positionX = 300;
        this.positionY = 300;
        image = setRandomImage();
        image.setFitHeight(32);
        image.setFitWidth(32);
        image.setLayoutX(positionX);
        image.setLayoutY(positionY);
        this.dead = false;

    }

    public void increaseMaxHp(int hp) {
        this.Hp = this.Hp + hp;
    }

    public void heal(int hp) {
        this.Hp += hp;
    }

    public void takeDmg(Player player,int atk) {
        this.Hp -= atk;
        if (this.Hp < 0) {
            player.takeXp();
            setDead(true);

        }
    }

    public void moveLeft(Map map) {
        if (checkWallLeft(map) == true) {
            this.positionX -= this.speed;
            image.setLayoutX(positionX);
        }
    }

    public void moveRight(Map map) {
        if (checkWallRight(map) == true) {
            this.positionX += this.speed;
            image.setLayoutX(positionX);
        }
    }

    public void moveUp(Map map) {
        if (checkWallUp(map) == true) {
            this.positionY -= this.speed;
            image.setLayoutY(positionY);
        }

    }

    public void moveDown(Map map) {
        if (checkWallDown(map) == true) {
            this.positionY += this.speed;
            image.setLayoutY(positionY);
        }
    }

    public ImageView getImage() {
        return this.image;
    }

    public boolean checkWallUp(Map map) {
        if (map.getMap()[positionX/32][positionY/32 - 2] instanceof Wall) {
            return false;
        } return true;
    }

    public boolean checkWallLeft(Map map) {
        if (map.getMap()[positionX/32 - 2][positionY/32] instanceof Wall) {
            return false;
        }return true;
    }

    public boolean checkWallDown(Map map) {
        if (map.getMap()[positionX/32][positionY/32 + 1] instanceof Wall) {
            return false;
        }return true;
    }

    public boolean checkWallRight(Map map) {
        if (map.getMap()[positionX/32 + 1][positionY/32] instanceof Wall) {
            return false;
        }return true;
    }

    public void move(Map map,Player player) {
        double x = Math.random() * 4;
        int rand = (int) x;
        if(!this.dead) {
            if (Math.abs(player.getPositionY() - positionY) < 32 && Math.abs(player.getPositionX() - positionX) < 32) {
                player.takeDmg(attack);
                return;
            }
        }
        switch(rand) {
            case 0:
                moveRight(map);
                break;
            case 1:
                moveLeft(map);
                break;
            case 2:
                moveUp(map);
                break;
            default:
                moveDown(map);
        }
    }

    public ImageView setRandomImage() {
        double x = Math.random() * 4;
        int rand = (int) x;
        ImageView image = new ImageView();
        switch(rand) {
            case 0:
                image = new ImageView(new Image(("menu/image/monster.gif")));
                break;
            case 1:
                image = new ImageView(new Image(("menu/image/fantom.gif")));
                break;
            case 2:
                image = new ImageView(new Image(("menu/image/boss.gif")));
                break;
            default:
                image = new ImageView(new Image(("menu/image/imp.gif")));
        }
        return image;
    }

    public int getHp() {
        return this.Hp;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public boolean getDead() {
        return this.dead;
    }

    public void setDead(boolean bool) {
        this.dead = bool;
    }

    public void setHp(int hp) {
        this.Hp = hp;
    }
}
