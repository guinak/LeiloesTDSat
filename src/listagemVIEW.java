import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.util.ArrayList;

public class listagemVIEW extends javax.swing.JFrame {

    public listagemVIEW() {
        initComponents();
        listarProdutos();
    }

    @SuppressWarnings("unchecked")
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        listaProdutos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        id_produto_venda = new javax.swing.JTextPane();
        btnVender = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        btnVendas = new javax.swing.JButton();
        btnVoltar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        listaProdutos.setModel(new DefaultTableModel(
            new Object [][] {},
            new String [] {
                "ID", "Nome", "Valor", "Status"
            }
        ));
        jScrollPane1.setViewportView(listaProdutos);

        jLabel1.setFont(new java.awt.Font("Lucida Fax", 0, 18)); // NOI18N
        jLabel1.setText("Lista de Produtos");

        jLabel2.setFont(new java.awt.Font("Lucida Fax", 0, 14)); // NOI18N
        jLabel2.setText("Vender Produto (ID)");

        jScrollPane2.setViewportView(id_produto_venda);

        btnVender.setText("Vender");
        btnVender.addActionListener(evt -> btnVenderActionPerformed(evt));

        btnVendas.setText("Consultar Vendas");
        btnVendas.addActionListener(evt -> btnVendasActionPerformed(evt));

        btnVoltar.setText("Voltar");
        btnVoltar.addActionListener(evt -> btnVoltarActionPerformed(evt));

        // Layout setup
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(47, 47, 47)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                            .addComponent(jLabel2)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 131, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                            .addComponent(btnVender))
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnVoltar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(btnVendas, javax.swing.GroupLayout.PREFERRED_SIZE, 168, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(jSeparator1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 440, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addContainerGap(49, Short.MAX_VALUE))
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1)
                    .addGap(193, 193, 193))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(26, 26, 26)
                    .addComponent(jLabel1)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(18, 18, 18)
                    .addComponent(jLabel2)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVender)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(btnVendas)
                        .addComponent(btnVoltar))
                    .addContainerGap(29, Short.MAX_VALUE))
        );

        pack();
    }

    private void listarProdutos() {
        ProdutosDAO produtosDAO = new ProdutosDAO();
        ArrayList<ProdutosDTO> lista = produtosDAO.listarProdutos();
        DefaultTableModel model = (DefaultTableModel) listaProdutos.getModel();
        model.setRowCount(0); // Limpa a tabela

        for (ProdutosDTO produto : lista) {
            model.addRow(new Object[]{produto.getId(), produto.getNome(), produto.getValor(), produto.getStatus()});
        }
    }

    private void btnVenderActionPerformed(java.awt.event.ActionEvent evt) {
        int id = Integer.parseInt(id_produto_venda.getText());
        ProdutosDAO produtosDAO = new ProdutosDAO();
        produtosDAO.venderProduto(id);
        listarProdutos(); // Atualiza a lista após a venda
    }

    private void btnVendasActionPerformed(java.awt.event.ActionEvent evt) {
        // Abre a tela de vendas
        new VendasVIEW().setVisible(true);
        this.dispose(); // Fecha a tela atual
    }

    private void btnVoltarActionPerformed(java.awt.event.ActionEvent evt) {
        // Voltar para a tela anterior
        new cadastroVIEW().setVisible(true);
        this.dispose(); // Fecha a tela atual
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new listagemVIEW().setVisible(true));
    }

    // Variables declaration
    private javax.swing.JButton btnVender;
    private javax.swing.JButton btnVendas;
    private javax.swing.JButton btnVoltar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable listaProdutos;
    private javax.swing.JTextPane id_produto_venda;
}
