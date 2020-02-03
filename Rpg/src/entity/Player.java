package entity;

import javafx.scene.image.*;
import map.*;
import menu.*;

public class Player {
    public int maxHp;
    public int Hp;
    public int attack;
    public int positionX;
    public int positionY;
    public int speed;
    public ImageView image = new ImageView(new Image(("menu/image/jean.gif")));
    public int level;
    public int xp;
    public int xpGoal;
    public int mana;
    public int manaMax;

    public Player(int hp, int attack, int speed,int size) {
        this.Hp = hp;
        this.maxHp = hp;
        this.attack = attack;
        this.positionX = 82;
        this.positionY = 82;
        image.setLayoutX(positionX);
        image.setLayoutY(positionY);
        this.speed = speed;
        image.setFitHeight(size);
        image.setFitWidth(size);
        this.level = 1;
        this.xpGoal = 5;
        this.xp = 0;
        this.mana = 1;
        this.manaMax = 1;
    }

    public void increaseMaxHp(int hp) {
        this.maxHp = this.maxHp + hp;
        this.Hp = this.Hp + hp;
    }

    public void heal(int hp, int manaUsed) {
        this.Hp += hp;
        useMana(manaUsed);
        if(this.Hp > this.maxHp) {
            Hp = maxHp;
        }
    }

    public void takeDmg(int atk) {
        this.Hp -= atk;
    }

    public void moveLeft(Map map) {
        if (checkWallLeft(map)) {
            this.positionX -= this.speed;
            image.setLayoutX(positionX);
        }
    }

    public void moveRight(Map map) {
        if (checkWallRight(map)) {
            this.positionX += this.speed;
            image.setLayoutX(positionX);
        }
    }

    public void moveUp(Map map) {
        if (checkWallUp(map)) {
            this.positionY -= this.speed;
            image.setLayoutY(positionY);
        }

    }

    public void moveDown(Map map) {
        if (checkWallDown(map)) {
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
        if (map.getMap()[positionX/32][positionY/32] instanceof Wall) {
            return false;
        }return true;
    }

    public boolean checkWallRight(Map map) {
        if (map.getMap()[positionX/32][positionY/32] instanceof Wall) {
            return false;
        }return true;
    }

    public int getHp() {
        return this.Hp;
    }

    public int getMaxHp() {
        return this.maxHp;
    }

    public int getPositionX() {
        return this.positionX;
    }

    public int getPositionY() {
        return this.positionY;
    }

    public int getAttack() {
        return this.attack;
    }

    public int getLevel() {
        return this.level;
    }

    public Boolean levelUp() {
        if (xp == xpGoal) {
            level ++;
            xpGoal++;
            xp = 0;
            maxHp += 5;
            attack += 2;
            if (level % 2 == 0) {
                this.manaMax++;
            }
            return true;
        }
        return false;
    }

    public void takeXp() {
        this.xp = this.xp + 1;
    }

    public int getXp() {
        return this.xp;
    }

    public int getXpGoal() {
        return this.xpGoal;
    }

    public void useMana(int manaUsed) {
        this.mana -= manaUsed;
    }

    public int getMana() {
        return this.mana;
    }

    public int getManaMax() {
        return this.manaMax;
    }

    public void manaRegen() {
        mana++;
        if (mana > manaMax) {
            mana = manaMax;
        }
    }

    public void setLocationX(int x) {
        this.positionX = x;
    }
    public void setLocationY(int y) {
        this.positionY = y;
    }
    public void setImage(String string, int size) {
        this.image = new ImageView(new Image((string)));
        image.setLayoutX(positionX);
        image.setLayoutY(positionY);
        image.setFitHeight(size);
        image.setFitWidth(size);
    }
}
