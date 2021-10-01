package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import model.interfaces.PlayingCard;

public class CardPanel extends JPanel {

	private final int num_card = 6;
	private final double space = 0.06;
	private final double height = 1.7;
	private final double suit_ratio = 0.26;
	private final double nameRatio = 0.1;

	private ArrayList<PlayingCard> cards = new ArrayList<PlayingCard>();

	public CardPanel() {
		setLayout(new BoxLayout(this, BoxLayout.X_AXIS));
		setBorder(BorderFactory.createLineBorder(Color.BLACK));
	}

	public void clearCards() {
		cards.clear();
		repaint();
	}

	public void drawNextCard(PlayingCard card) {
		cards.add(card);
		repaint();
	}

	public void drawBustCard(PlayingCard card) {
		cards.add(card);
		repaint();
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (cards.isEmpty()) {
			return;
		}

		int cardSpacing = (int) ((this.getWidth() / num_card) * space);
		double cardWidth = (this.getWidth() - cardSpacing * (num_card + 1)) / num_card;
		double cardHeight = cardWidth * height;
		Dimension cardDimension = new Dimension((int) cardWidth, (int) cardHeight);
		Point topLeftCorner = new Point(cardSpacing, cardSpacing);

		PlayingCard bustCard = cards.get(cards.size() - 1);

		for (PlayingCard card : cards) {
			if (card == bustCard) {
				drawGrayCard(g, topLeftCorner, cardDimension);
			} else {
				drawCard(g, topLeftCorner, cardDimension);
			}
			drawCardValue(g, card, topLeftCorner, cardDimension);
			drawCardSuit(g, card, topLeftCorner, cardDimension);
			topLeftCorner.x += cardDimension.width + cardSpacing;
		}

		g.dispose();
	}

	/****
	 * drawing
	 */
	private void drawCard(Graphics g, Point topLeftCorner, Dimension cDimension) {
		g.setColor(Color.WHITE);
		g.fillRoundRect(topLeftCorner.x, topLeftCorner.y, cDimension.width, cDimension.height, 30, 30);
	}
  // last one grey
	private void drawGrayCard(Graphics g, Point topLeftCorner, Dimension cDimension) {
		g.setColor(Color.LIGHT_GRAY);
		g.fillRoundRect(topLeftCorner.x, topLeftCorner.y, cDimension.width, cDimension.height, 30, 30);
	}
    // value part
	private void drawCardValue(Graphics g, PlayingCard card, Point topLeftCorner, Dimension cDimension) {
		String cardName = getCardName(card);
		g.setColor(getCardColor(card));
		int space = (int) (cDimension.width * nameRatio);
		g.setFont(new Font("default", Font.ITALIC, space));
		Dimension cardNameSize = new Dimension(g.getFontMetrics().stringWidth(cardName),
				(int) g.getFontMetrics().getLineMetrics(cardName, g).getHeight());

		g.drawString(cardName, topLeftCorner.x + space, topLeftCorner.y + space + (int) (cardNameSize.height / 2));
		g.drawString(cardName, topLeftCorner.x + cDimension.width - cardNameSize.width - space,
				topLeftCorner.y + cDimension.height - space);
	}
    //suit part
	private void drawCardSuit(Graphics g, PlayingCard card, Point left_corner, Dimension cDimension) {
		BufferedImage image;
		try {
			System.out.println(getURL(card));
			image = ImageIO.read(getURL(card));
			int width = (int) (cDimension.width * suit_ratio);
			int x = left_corner.x + cDimension.width / 2 - width / 2;
			int y = left_corner.y + cDimension.height / 2 - width / 2;
			g.drawImage(image, x, y, width, width, null);

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
// getting image
	private URL getURL(PlayingCard card) {

		String suit;
		switch (card.getSuit()) {
		case DIAMONDS:
			suit = "diamonds.png";
			break;

		case HEARTS:
			suit = "hearts.png";
			break;

		case CLUBS:
			suit = "clubs.png";
			break;

		default:
			suit = "spades.png";
			break;
		}
		return this.getClass().getResource("/img/" + suit);
	}

	private String getCardName(PlayingCard card) {
		switch (card.getValue()) {
		case EIGHT:
			return "8";
		case NINE:
			return "9";
		case TEN:
			return "10";
		default:
			return card.getValue().toString().substring(0, 1);
		}
	}
  // Color separation 
	private Color getCardColor(PlayingCard card) {
		switch (card.getSuit()) {
		case DIAMONDS:
		case HEARTS:
			return Color.RED;
		case CLUBS:
		case SPADES:
		default:
			return Color.BLACK;
		}
	}
}
