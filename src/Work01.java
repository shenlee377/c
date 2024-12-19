import java.util.Random;
import java.util.Scanner;

public class Work01 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Random random = new Random(); // 隨機變數
        
        System.out.println("====== 歡迎來到魔王的樂園 ======");
        
        // 第一部分：檢查輸入，直到輸入正確
        String start = "";
        while (!start.equals("開始")) {
            System.out.println("請輸入 '開始' 來開始遊戲");
            start = scanner.nextLine().trim();
            if (!start.equals("開始")) {
                System.out.println("輸入無效，請重新輸入！");
            }
        }

        System.out.println("魔王說: 勇敢的冒險者，歡迎來到我的樂園，想要解救自己的家園就上來城堡打敗我吧!!!");
        System.out.println("魔王說: 哈哈哈");
        System.out.println("勇者氣憤說: 可惡的魔王，都是你害我的家園燒毀的！");
        System.out.println("勇者說: 我一定打倒你！");
        System.out.println("魔王笑著說: 那就登上塔上來打敗我哈哈哈！");
        
        // 後續遊戲邏輯（保留原有內容）
        
        String[] rps = {"剪刀", "石頭", "布"}; // 隨機資料庫內容
        int round = 1; // 回合數
        int playerHealth = 100;
        int bossHealth = 100;
        int bossDefense = 0; // Boss 抵消攻擊的次數

        while (playerHealth > 0 && bossHealth > 0) {
            System.out.println("=================================");
            System.out.println("第 " + round + " 回合");
            System.out.println("你的血量: " + playerHealth);
            System.out.println("魔王的血量: " + bossHealth);
            System.out.println("請選擇你要攻擊的方式 (剪刀, 石頭, 布)。如果想放棄，請輸入: 放棄");

            String player = scanner.nextLine();

            if (player.equals("放棄")) {
                System.out.println("你選擇了撤退，遊戲結束。");
                break;
            }

            // 玩家選擇的檢查
            if (!player.equals("剪刀") && !player.equals("石頭") && !player.equals("布")) {
                System.out.println("無效的選擇，請再試一次。");
                continue;
            }

            int attack = random.nextInt(3); // 攻擊方式只有3種
            String devil = rps[attack]; // 魔王的選擇變數
            System.out.println("魔王的選擇: " + devil);

            // 判斷勝負
            if (player.equals(devil)) { // 如果與玩家選擇一樣，則平手
                System.out.println("攻擊互相抵消，沒什麼效果！");
            } else if (
                (player.equals("石頭") && devil.equals("剪刀")) ||
                (player.equals("布") && devil.equals("石頭")) ||
                (player.equals("剪刀") && devil.equals("布"))
            ) {
                System.out.println("結果: 你贏了！");

                // 檢查 Boss 是否處於低血量並能抵消攻擊
                if (bossHealth <= 20 && bossDefense < 3) {
                    bossDefense++; // 增加抵消次數
                    System.out.println("魔王防禦住了你的攻擊！(剩餘防禦次數: " + (3 - bossDefense) + ")");
                } else {
                    bossHealth -= 20;
                    System.out.println("你對魔王造成了 20 點傷害！");
                }

            } else {
                System.out.println("結果: 你輸了！");
                playerHealth -= 20;
                System.out.println("魔王對你造成了 20 點傷害！");
            }

            // 檢查玩家血量
            if (playerHealth <= 0) { // 第四部分
                System.out.println("YOU DIED");
                System.out.println("魔王: 勇者，你就永遠困在我的樂園吧！");
                System.out.println("即將為你重新開始，請等待3秒");
                try {
                    Thread.sleep(3000); // 等待 3 秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("是否要重新開始遊戲？（是/否）");
                String response = scanner.nextLine().trim();
                if (response.equals("是")) {
                    playerHealth = 100;
                    bossHealth = 100;
                    bossDefense = 0; // 重置防禦次數
                    round = 0; // 重置回合計數
                } else {
                    System.out.println("遊戲結束，感謝遊玩!!!");
                    break;
                }
            }

            // 檢查魔王血量
            if (bossHealth <= 0) {
                System.out.println("恭喜你打敗了魔王！");
                System.out.println("魔王: 可惡!!! 我竟然輸了，我還會回來復仇的！");
                System.out.println("你成功封印了魔王");
                System.out.println("希望和平可以一直繼續！");
                System.out.println("即將為你重新開始，請等待3秒");
                try {
                    Thread.sleep(3000); // 等待 3 秒
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                }
                System.out.println("是否要重新開始遊戲？（是/否）");
                String response = scanner.nextLine().trim();
                if (response.equals("是")) {
                    playerHealth = 100;
                    bossHealth = 100;
                    bossDefense = 0; // 重置防禦次數
                    round = 0; // 重置回合計數
                } else {
                    System.out.println("遊戲結束，感謝遊玩!!!");
                break; // 結束遊戲
                }
            }

            round++; // 增加回合計數
        }

        scanner.close(); // 最後關閉 Scanner
    }
}
