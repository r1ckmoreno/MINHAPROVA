import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class VotacaoBigBrotherBrasilGUI {
    public static void main(String[] args) {
        ArrayList<Jogador> jogadores = new ArrayList<>();
        jogadores.add(new Jogador("Alane Dias"));
        jogadores.add(new Jogador("Beatriz Reis"));
        jogadores.add(new Jogador("Davi Brito"));
        jogadores.add(new Jogador("Deniziane Ferreira"));
        jogadores.add(new Jogador("Fernanda Bande"));
        jogadores.add(new Jogador("Giovanna Lima"));
        jogadores.add(new Jogador("Giovanna Pitel"));
        jogadores.add(new Jogador("Isabelle Nogueira"));
        jogadores.add(new Jogador("Juninho"));
        jogadores.add(new Jogador("Leidy Elin"));
        jogadores.add(new Jogador("Lucas Henrique"));
        jogadores.add(new Jogador("Lucas Luigi"));
        jogadores.add(new Jogador("Lucas Pizane"));
        jogadores.add(new Jogador("Marcus Vinicius"));
        jogadores.add(new Jogador("Matteus Amaral"));
        jogadores.add(new Jogador("Maycon Cosmer"));
        jogadores.add(new Jogador("MC Bin Laden"));
        jogadores.add(new Jogador("Michel Nogueira"));
        jogadores.add(new Jogador("Nizam"));
        jogadores.add(new Jogador("Raquele Cardozo"));
        jogadores.add(new Jogador("Rodriguinho"));
        jogadores.add(new Jogador("Thalyta Alves"));
        jogadores.add(new Jogador("Vanessa Lopes"));
        jogadores.add(new Jogador("Vinicius Rodrigues"));
        jogadores.add(new Jogador("Wanessa Camargo"));
        jogadores.add(new Jogador("Yasmin Brunet"));

        JFrame frame = new JFrame("Big Brother Brasil");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(250, 200);
        frame.setLayout(new BorderLayout());
        frame.setLocationRelativeTo(null);

        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.Y_AXIS));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

        JButton voteButton = new JButton("Votar");
        voteButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        voteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String voto;
                do {
                    voto = JOptionPane.showInputDialog(null, "Em quem você vota para sair da casa? (digite sair para voltar ao menu principal)");
                    if (voto != null && !voto.equalsIgnoreCase("sair")) {
                        boolean encontrado = false;
                        for (Jogador jogador : jogadores) {
                            if (jogador.getNome().equalsIgnoreCase(voto)) {
                                jogador.incrementaUmVoto();
                                encontrado = true;
                                JOptionPane.showMessageDialog(null, "Voto computado com sucesso!");
                                break;
                            }
                        }
                        if (!encontrado) {
                            JOptionPane.showMessageDialog(null, "Jogador não encontrado na lista.");
                        }
                    }
                } while (voto != null && !voto.equalsIgnoreCase("sair"));
            }
        });

        JButton resultButton = new JButton("Apurar resultado");
        resultButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        resultButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ArrayList<Jogador> eliminados = new ArrayList<>();
                int maiorNumeroVotos = -1;

                for (Jogador jogador : jogadores) {
                    int votos = jogador.getVotos();
                    if (votos > maiorNumeroVotos) {
                        eliminados.clear();
                        eliminados.add(jogador);
                        maiorNumeroVotos = votos;
                    } else if (votos == maiorNumeroVotos) {
                        eliminados.add(jogador);
                    }
                }

                if (eliminados.size() == 1) {
                    Jogador eliminado = eliminados.get(0);
                    JOptionPane.showMessageDialog(null, "Se eu conseguir mover montanhas, se eu conseguir surfar um tsunami,\n" +
                            "se eu conseguir domar o sol, se eu conseguir fazer o mar virar sertão,\n" +
                            "e o sertão virar mar, se eu conseguir dizer o que eu nunca vou conseguir dizer,\n" +
                            "aí terá chegado o dia em que eu vou conseguir te eliminar com alegria.\n" +
                            "Com " + eliminado.getVotos() + " votos, é você quem sai " + eliminado.getNome());
                } else {
                    JFrame empateFrame = new JFrame("Empate - Novo Voto");
                    empateFrame.setSize(700, 300);
                    empateFrame.setLayout(new BorderLayout());
                    empateFrame.setLocationRelativeTo(null);

                    JPanel empateButtonPanel = new JPanel();
                    empateButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
                    empateButtonPanel.setBorder(BorderFactory.createEmptyBorder(30, 0, 30, 0));

                    JLabel empateLabel = new JLabel("Empate entre os jogadores com " + maiorNumeroVotos + " votos. Realize uma nova votação para desempate.");

                    empateButtonPanel.add(empateLabel);

                    for (Jogador jogador : eliminados) {
                        JButton empateVoteButton = new JButton("Votar em " + jogador.getNome());
                        empateVoteButton.addActionListener(new ActionListener() {
                            @Override
                            public void actionPerformed(ActionEvent e) {
                                jogador.incrementaUmVoto();
                                JOptionPane.showMessageDialog(null, "Voto computado com sucesso!");
                                empateFrame.dispose();
                            }
                        });
                        empateButtonPanel.add(empateVoteButton);
                    }

                    empateFrame.add(empateButtonPanel, BorderLayout.CENTER);
                    empateFrame.setVisible(true);
                }
            }
        });

        buttonPanel.add(voteButton);
        buttonPanel.add(Box.createRigidArea(new Dimension(0, 25)));
        buttonPanel.add(resultButton);

        frame.add(buttonPanel, BorderLayout.CENTER);

        frame.setVisible(true);
    }
}
