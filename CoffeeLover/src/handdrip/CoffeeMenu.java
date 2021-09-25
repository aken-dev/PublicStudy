
package handdrip;

import java.util.Scanner;

interface CoffeeMaker{
	int water_temperature = 100;
	int water_ml = 100;
	int grind_level = 100;
	int beans_g = 100;
	int xxx = 100;
	int making();
}

class CoffeeMenu {

	public static void main() {
		// TODO 自動生成されたメソッド・スタブ
		Scanner sc = new Scanner(System.in);


		while(true) {
			System.out.println("どんな珈琲を作りますか？");
			System.out.print("(1)ホットコーヒー　(2)アイスコーヒー　(3)コーヒーゼリー　" +
				 "(0)終了：");
			int choise = sc.nextInt();
			if (choise == 0) break;
			switch(choise) {
				case 1:
					System.out.println("【ホットコーヒーを作ります。】");
					CoffeeMaker hc = new HotCoffee(90, 550, 5, 30);
					hc.making();
					break;
				case 2:
					System.out.println("【アイスコーヒーを作ります。】");
					CoffeeMaker ic = new IcedCoffee(90, 300, 5, 45, 250);
					ic.making();
					break;
				case 3:
					System.out.println("【コーヒーゼリーを作ります。】");
					CoffeeMaker cj = new CoffeeJelly(90, 350, 5, 47, 200, 25, 10);
					cj.making();
					break;
			}
		}
		sc.close();
	}
}

class BoiledWater {
	 int temperature;
	 int ml;
	 BoiledWater(int temperature, int ml) {
		this.temperature = temperature;
		this.ml = ml;
		System.out.println(temperature + "℃のお湯を" + ml + "ml用意しました。");
	}
}
 class GroundCoffeeBeans {
	int grind_level; //エスプレッソ=2,  ドリップコーヒー=5,  サイフォン=8
	int g;
	 GroundCoffeeBeans(int grind_level, int g){
		this.grind_level = grind_level;
		this.g = g;
		System.out.println(g + "gの珈琲豆を【挽き目" + grind_level + "】の細かさで挽きました。");
	}
}
 class HandDrip {
	BoiledWater water;
	GroundCoffeeBeans beans;
	int paper_filter = 1;

	HandDrip(BoiledWater water, GroundCoffeeBeans beans){
		this.water = water;
		this.beans = beans;
	}
	 int dripping(){
		int liquid = 0;
		pre_beans_setting(); //珈琲豆をセット
		pre_infusion(); //蒸らし
		System.out.println("抽出を開始します。");
		System.out.println("お湯の温度は" + water.temperature + "℃です。");
		System.out.println("今から３回に分けて、粉の中央にゆっくりお湯を注いでいきます。");
		liquid += water.ml / 3;
		water.ml -= water.ml / 3;
		System.out.println("１投目のお湯を注ぎました。");
		waiting(7);
		liquid += water.ml / 2;
		water.ml -= water.ml / 2;
		System.out.println("２投目のお湯を注ぎました。");
		waiting(10);
		liquid += water.ml;
		water.ml -= water.ml;
		System.out.println("３投目のお湯を注ぎました。");
		waiting(10);
		System.out.println("抽出完了");
		return liquid;
	}
	void pre_beans_setting() {
		System.out.println("ドリッパー（抽出器具）にペーパーフィルターをセットし、挽き豆" + beans.g + "g"
				+ "を充填しました。");
		beans.g -= beans.g;
		paper_filter -= 1;
	}
	void pre_infusion(){
		water.ml -= 50;
		System.out.println("まんべんなく少量のお湯を掛けて、粉表面の全体を湿らしました。");
		System.out.println("30秒ほど放置して粉を蒸らします。");
		waiting(30);
		System.out.println("蒸らし行程が完了しました。");
	}
	void waiting(int sec) {
		try {
		    Thread.sleep(sec * 1000);
		} catch (InterruptedException e) {
		    e.printStackTrace();
		}
		System.out.println(sec + " 秒 待ちました。");
	}
}
 class CoffeeBase implements CoffeeMaker{
	int water_temperature;
	int water_ml;
	int grind_level;
	int beans_g;

	 CoffeeBase(int water_temperature, int water_ml, int grind_level, int beans_g) {
		this.water_temperature = water_temperature;
		this.water_ml = water_ml;
		this.grind_level = grind_level;
		this.beans_g = beans_g;
	}
	 public int making() {
		BoiledWater water = new BoiledWater(water_temperature, water_ml);
		GroundCoffeeBeans beans = new GroundCoffeeBeans(grind_level, beans_g);
		HandDrip drip = new HandDrip(water, beans);
		int liquid = drip.dripping();
		System.out.println(liquid + "mlの珈琲液を抽出しました。");
		return liquid;
	}
}
 class HotCoffee extends CoffeeBase {
	 HotCoffee(int water_temperature, int water_ml, int grind_level, int beans_g){
		 super(water_temperature, water_ml, grind_level, beans_g);
	 }
 }
 class IcedCoffee extends CoffeeBase {
	int ice;
	IcedCoffee(int water_temperature, int water_ml,	int grind_level, int beans_g,int ice){
		super(water_temperature, water_ml,	grind_level, beans_g);
		this.ice = ice;
	}
	public int making() {
		System.out.println("サーバー（容器）に氷" + ice + "gをセットしました。");
		System.out.println("氷が入った容器の真上にドリッパー（抽出器具）をセットして、珈琲を淹れていきます。");
		int liquid = ice + super.making();
		System.out.println("約" + liquid + "mlのアイスコーヒーが出来ました。");
		return liquid;
	}
}

 class CoffeeJelly extends CoffeeBase {
	int ice;
	int sugar;
	int gelatin;
	CoffeeJelly(int water_temperature, int water_ml, int grind_level, int beans_g,int ice, int sugar,int gelatin){
		super(water_temperature, water_ml,	grind_level, beans_g);
		this.ice = ice;
		this.sugar = sugar;
		this.gelatin = gelatin;
	}
	public int making() {
		int liquid = ice + super.making();
		System.out.println("ゼラチン" + gelatin + "gを投入し、よく混ぜ溶かしました。");
		System.out.println("砂糖" + sugar + "gを投入し、よく混ぜ溶かしました。");
		System.out.println("氷" + ice + "gを投入し、よく混ぜ溶かしました。");
		System.out.println("１食分ずつグラスに注ぎ、小分けにしました。");
		System.out.println("冷蔵庫で３時間冷やし固めました。");
		System.out.println("約" + liquid + "ccのコーヒーゼリーが出来ました。");
		return liquid;
	}
}
