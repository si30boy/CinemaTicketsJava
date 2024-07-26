import javax.swing.*;

import java.awt.image.BufferedImage;


public class AppFrame extends JFrame {
    LoginPage panelLogin; // 0
    RegisterPage panelRegister; // 1
    StorePage panelStore; // 2
    BasketShop panelBasketShop; // BasketShop panel
    StorePageAdmin panelStoreAdmin;
    AddMovieAdmin panelAddMovie;
    BasketShop basketShop;
    OrderShopItem panelOrderShopItem;
    ProfilePage panelProfile;
    DataMoviePage panelDataMovie;
    ResultBuyPage panelResultBuyPage;
    DataMoviePageAdmin panelDataMovieAdmin;
    DataBasketShopAdmin panelDataBasketShopAdmin;
    DataSansCinemaAdmin panelDataSansCinemaAdmin;
    CreditCardDataAdmin panelCreditCardDataAdmin;

    AppFrame(int panel) {
        panelLogin = new LoginPage(this);
        panelRegister = new RegisterPage(this);
        panelStore = new StorePage(this); // Pass 'this' to StorePage
        panelBasketShop = new BasketShop(this); // Assuming you have BasketShop class with similar structure
        panelStoreAdmin =new StorePageAdmin(this);
        panelAddMovie = new AddMovieAdmin(this);
        basketShop = new BasketShop(this);
        panelOrderShopItem =new OrderShopItem(this);
        panelProfile = new ProfilePage(this);
        panelDataMovie = null;
        panelResultBuyPage = new ResultBuyPage(this);
        panelDataMovieAdmin = null;
        panelDataBasketShopAdmin = new DataBasketShopAdmin(this);
        panelDataSansCinemaAdmin = new DataSansCinemaAdmin(this);
        panelCreditCardDataAdmin = new CreditCardDataAdmin(this);


        if (panel == 0) {
            this.add(panelLogin);
        }


        this.setTitle("Ticket Cinema");
        this.pack();
        this.setVisible(true);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    public void showLogin() {
        this.getContentPane().removeAll();
        this.add(panelLogin);
        this.revalidate();
        this.repaint();
    }
    public void showOrderShopItem() {
        this.getContentPane().removeAll();
        this.add(panelOrderShopItem);
        this.revalidate();
        this.repaint();
    }

    public void showRegister() {
        this.getContentPane().removeAll();
        this.add(panelRegister);
        this.revalidate();
        this.repaint();
    }

    public void showBasketShop() {
        this.getContentPane().removeAll();
        this.add(panelBasketShop);
        panelBasketShop.refreshBooks(1); // Refresh the BasketShop panel
        this.revalidate();
        this.repaint();
    }

    public void showAddBookAdmin() {
        this.getContentPane().removeAll();
        this.add(panelAddMovie);
        this.revalidate();
        this.repaint();
    }
    public ProfilePage getProfilePage() {
        return panelProfile;
    }


    public void showStore() {
        this.getContentPane().removeAll();
        this.add(panelStore);
        this.revalidate();
        this.repaint();
    }

    public void showDataSansAdmin() {
        this.getContentPane().removeAll();
        this.add(panelDataSansCinemaAdmin);
        this.revalidate();
        this.repaint();
    }

    public void showDataBasketShopAdmin() {
        this.getContentPane().removeAll();
        this.add(panelDataBasketShopAdmin);
        this.revalidate();
        this.repaint();
    }
    public void showCreditCardDataAdmin() {
        this.getContentPane().removeAll();
        this.add(panelCreditCardDataAdmin);
        this.revalidate();
        this.repaint();
    }

    public void showStoreAdmin() {
        this.getContentPane().removeAll();
        this.add(panelStoreAdmin);
        this.revalidate();
        this.repaint();
    }


    public void showProfile() {
        this.getContentPane().removeAll();
        panelProfile = new ProfilePage(this);
        this.add(panelProfile);
        this.revalidate();
        this.repaint();
    }


    public void showResultBuyPage() {
        this.getContentPane().removeAll();
        this.add(panelResultBuyPage);
        panelResultBuyPage.refreshBooks(); // Refresh the BasketShop panel
        this.revalidate();
        this.repaint();
    }

    public void showDataMovie(BufferedImage moviePicture, String cinemaName, String directorName, String movieName, int Price) {
        this.getContentPane().removeAll();
        panelDataMovie = new DataMoviePage(this, moviePicture, cinemaName, directorName, movieName , Price);
        this.add(panelDataMovie);
        this.revalidate();
        this.repaint();
    }

    public void showDataMovieAdmin(BufferedImage moviePicture, String cinemaName, String directorName, String movieName, int Price) {
        this.getContentPane().removeAll();
        panelDataMovieAdmin = new DataMoviePageAdmin(this, moviePicture, cinemaName, directorName, movieName , Price);
        this.add(panelDataMovieAdmin);
        this.revalidate();
        this.repaint();
    }


    public BasketShop getBasketShop() {
        return basketShop;
    }

    public ResultBuyPage getResultBuyPage() {
        return panelResultBuyPage;
    }
}