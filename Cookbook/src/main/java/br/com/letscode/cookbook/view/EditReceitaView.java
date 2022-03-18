package br.com.letscode.cookbook.view;

import br.com.letscode.cookbook.domain.Ingrediente;
import br.com.letscode.cookbook.domain.Receita;
import br.com.letscode.cookbook.domain.Rendimento;
import br.com.letscode.cookbook.enums.Categoria;
import br.com.letscode.cookbook.enums.TipoMedida;
import br.com.letscode.cookbook.enums.TipoRendimento;

import java.util.Locale;

public class EditReceitaView {
    private final Receita receita;

    public EditReceitaView(Receita receita) {
        this.receita = new Receita(receita);
    }

    public Receita edit() {
        do {
            new ReceitaView(receita).fullView(System.out);
        } while (showMenuEdit());

        String opcao = ConsoleUtils.getUserOption("Antes de sair, gostaria de salvar a receita " + receita.getNome() + "?\nS - Sim   N - Não", "S", "N");
        if ("S".equalsIgnoreCase(opcao)) {
            return receita;
        } else {
            return null;
        }
    }
    private boolean showMenuEdit() {
        String[] options = new String[9];
        StringBuilder sb = new StringBuilder("#".repeat(100));
        sb.append("%n").append("Selecione a alteração desejada");
        sb.append("%n").append("#".repeat(100));
        sb.append("%n").append("  1 : Nome  %n");
        options[0] = "1";
        sb.append("  2 : Categoria  %n");
        options[1] = "2";
        sb.append("  3 : Tempo de Preparo  %n");
        options[2] = "3";
        sb.append("  4 : Rendimento  %n");
        options[3] = "4";
        sb.append("  5 : Adicionar Ingrediente  %n");
        options[4] = "5";
        if (receita.getIngredientes().size() != 0) {
        sb.append("  6 : Remover Ingrediente  %n");
        options[5] = "6";
        }
        sb.append("  7 : Adicionar Preparo  %n");
        options[6] = "7";
        if (receita.getPreparo().size() != 0) {
        sb.append("  8 : Remover Preparo  %n");
        options[7] = "8";
        }
        sb.append("  # ").append("# ".repeat(48)).append("%n");
        sb.append("  X : Sair  %n");
        options[8] = "X";
        sb.append("#".repeat(100)).append("%n");

        String opcao = ConsoleUtils.getUserOption(sb.toString(), options).toUpperCase(Locale.getDefault());
        switch (opcao) {
            case "1":
                nome();
                break;
            case "2":
                categoria();
                break;
            case "3":
                tempoPreparo();
                break;
            case "4":
                rendimento();
                break;
            case "5":
                addIngrediente();
                break;
            case "6":
                remIngrediente();
                break;
            case "7":
                addPreparo();
                break;
            case "8":
                remPreparo();
                break;
            case "X":
                System.out.println("Retornando ao Menu Principal\n");
                return false;
            default:
                System.out.println("Opção inválida!!!");
        }
        return true;
    }
    private void nome() {
        String name = ConsoleUtils.getUserInput("Informe o novo nome da receita?");
        if ((name != null) && (!name.isBlank())) {
            receita.setNome(name);
        }
    }

    private void tempoPreparo() {
        while (true) {
            String time = ConsoleUtils.getUserInput("Informe o novo tempo de preparo");
            if (!time.isBlank()) {
                try {
                    double value = Double.parseDouble(time);
                    receita.setTempoPreparo(value);
                    return;
                } catch (NullPointerException | NumberFormatException e) {
                    System.out.println("Tempo de preparo inválido. Gentileza revisar.");
                }
            } else {
                break;
            }
        }
    }

    private void categoria() {
        StringBuilder sb = new StringBuilder("Informa a nova categoria da receita\n");
        String[] options = new String[Categoria.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, Categoria.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        Categoria categoria = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                categoria = Categoria.values()[i];
                break;
            }
        }
        receita.setCategoria(categoria);
    }

    public void rendimento() {
        int valueMin;
        int valueMax;
        while(true){
            String min = ConsoleUtils.getUserInput("Informe o rendimento mínimo desta receita");
            try {
                if (min != null) {
                    valueMin = Integer.parseInt(min);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("O rendimento mínimo é inválido. Gentileza revisar");
            }
        }
        while(true) {
            String max = ConsoleUtils.getUserInput("Informe o rendimento máximo desta receita");
            try {
                if (max != null) {
                    valueMax = Integer.parseInt(max);
                    break;
                }
            } catch (NumberFormatException e) {
                System.out.println("O rendimento máximo é inválido. Gentileza revisar.");
            }
        }

        TipoRendimento tipoRendimento = editTipoRendimento();
        if (valueMin == valueMax) {
            receita.setRendimento(new Rendimento(valueMin, tipoRendimento));
        } else {
            receita.setRendimento(new Rendimento(valueMin, valueMax, tipoRendimento));
        }
    }

    private TipoRendimento editTipoRendimento() {
        StringBuilder sb = new StringBuilder("Informe o tipo de rendimento para esta receita.\n");
        String[] options = new String[TipoRendimento.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoRendimento.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoRendimento tipoRendimento = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoRendimento = TipoRendimento.values()[i];
                break;
            }
        }
        return tipoRendimento;
    }

    private void addIngrediente() {
        double quantidade;
        String name = ConsoleUtils.getUserInput(" Informe o nome do ingrediente.");
        if (name.isBlank()) {
            return;
        }

        while(true) {
            String qtd = ConsoleUtils.getUserInput("Informe a quantidade de ingrediente.");
            try {
                if (qtd != null) {
                    quantidade = Double.parseDouble(qtd);
                    break;
                }
            } catch (NullPointerException | NumberFormatException e) {
                System.out.println("A quantidade de ingrediente informada é invalida. Gentileza revisar.");
            }
        }

        TipoMedida tipoMedida = addTipoMedida();
        receita.getIngredientes().add(new Ingrediente(name, quantidade, tipoMedida));
    }

    private TipoMedida addTipoMedida() {
        StringBuilder sb = new StringBuilder("Informe o tipo de medida deste ingrediente\n");
        String[] options = new String[TipoMedida.values().length];
        for (int i = 0; i < options.length; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, TipoMedida.values()[i]));
        }
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        TipoMedida tipoMedida = null;
        for (int i = 0; i < options.length; i++) {
            if (opcao.equalsIgnoreCase(options[i])) {
                tipoMedida = TipoMedida.values()[i];
                break;
            }
        }
        return tipoMedida;
    }

    private void remIngrediente() {
        StringBuilder sb = new StringBuilder("Informe o ingrediente que deseja remover.\n");
        String[] options = new String[receita.getIngredientes().size()+1];
        for (int i = 0; i < options.length-1; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, receita.getIngredientes().get(i).toString()));
        }
        options[options.length-1] = "X";
        sb.append("X - Sair");
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        if (!"X".equalsIgnoreCase(opcao)) {
            for (int i = 0; i < options.length - 1; i++) {
                if (opcao.equalsIgnoreCase(options[i])) {
                    receita.getIngredientes().remove(i);
                    break;
                }
            }
        }
    }

    private void addPreparo() {
        String preparo = ConsoleUtils.getUserInput("Informe o preparo que deseja incluir:");
        if (preparo.isBlank()) {
            return;
        }
        if (receita.getPreparo().size() == 0) {
            receita.getPreparo().add(preparo);
        } else {
            StringBuilder sb = new StringBuilder("Informe em qual ponto da receita que deseja incluir este passo:\n");
            String[] options = new String[receita.getPreparo().size()+1];
            for (int i = 0; i < options.length-1; i++) {
                options[i] = String.valueOf(i);
                sb.append(String.format("%d - %s%n", i, receita.getPreparo().get(i)));
            }
            options[options.length-1] = String.valueOf(options.length-1);
            sb.append(String.format("%d - %s%n", options.length-1, ""));
            String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
            for (int i = 0; i < options.length; i++) {
                if (opcao.equalsIgnoreCase(options[i])) {
                    receita.getPreparo().add(i, preparo);
                    break;
                }
            }
        }
    }

    private void remPreparo() {
        StringBuilder sb = new StringBuilder("Informe o passo do preparo que deseja remover:\n");
        String[] options = new String[receita.getPreparo().size()+1];
        for (int i = 0; i < options.length-1; i++) {
            options[i] = String.valueOf(i);
            sb.append(String.format("%d - %s%n", i, receita.getPreparo().get(i)));
        }
        options[options.length-1] = "X";
        sb.append("X - Sair");
        String opcao = ConsoleUtils.getUserOption(sb.toString(), options);
        if (!"X".equalsIgnoreCase(opcao)) {
            for (int i = 0; i < options.length - 1; i++) {
                if (opcao.equalsIgnoreCase(options[i])) {
                    receita.getPreparo().remove(i);
                    break;
                }
            }
        }
    }
}