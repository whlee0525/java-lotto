package lotto.domain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LottoGame {

    static final int TICKET_PRICE = 1000;

    private List<LottoTicket> tickets;
    private int[] winCounts;
    private double winPercent;


    public LottoGame(int money) {
        if (money < TICKET_PRICE) {
            throw new RuntimeException();
        }
        int ticketCount = money / TICKET_PRICE;
        this.tickets = generateLottoTickets(ticketCount);
    }

    public LottoGame(List<LottoTicket> tickets) {
        this.tickets = tickets;
    }

    public List<String> getTicketsString() {
        List<String> strings = new ArrayList<>();
        for (LottoTicket ticket : tickets) {
            strings.add(ticket.toString());
        }
        return strings;
    }

    private List<LottoTicket> generateLottoTickets(int ticketCount) {
        List<LottoTicket> tickets = new ArrayList<>();
        for (int i = 0; i < ticketCount; i++) {
            tickets.add(new LottoTicket());
        }
        return tickets;
    }

    public void doGame(String winText, int bonus) {
        LottoTicket winTicket = new LottoTicket(winText);

        if (!isValidBonus(winTicket, bonus)) {
            throw new RuntimeException();
        }

        this.winCounts = calculateWinCounts(winTicket, bonus);
        this.winPercent = calculateWinPercent();
    }

    private boolean isValidBonus(LottoTicket winTicket, int bonus) {
        return !(winTicket.contains(bonus)
                || bonus < NumberGenerator.LOTTO_START_NUM
                || bonus > NumberGenerator.LOTTO_END_NUM);
    }

    private int[] calculateWinCounts(LottoTicket winTicket, int bonus) {
        int[] winCounts = new int[Rank.values().length];

        for (LottoTicket ticket : tickets) {
            Rank rank = ticket.calculateRank(winTicket, bonus);
            winCounts[rank.ordinal()]++;
        }

        return winCounts;
    }

    /*private List<String> makeStatisticsStrings(int[] statistics) {
        List<String> statisticsStrings = new ArrayList<>();
        for (int i = 3; i <= 6; i++) {
            String resultString = String.format("%d개 일치 (%d원)- %d개", i, PRIZE[i], statistics[i]);
            statisticsStrings.add(resultString);
        }
        return statisticsStrings;
    }*/

    private double calculateWinPercent() {
        int consume = tickets.size() * TICKET_PRICE;
        int income = calculateIncome();

        return Math.round((double) income / consume * 10000) / 100.0;
    }

    private int calculateIncome() {
        return Arrays.stream(Rank.values())
                .mapToInt(rank -> rank.getWinMoney() * winCounts[rank.ordinal()])
                .sum();
    }

    /*private String makeTakePercentString(double takePercent) {
        return "총 수익률은 " + takePercent + "입니다.";
    }*/

    public int[] getWinCounts() {
        return winCounts;
    }

    public double getWinPercent() {
        return winPercent;
    }


}
