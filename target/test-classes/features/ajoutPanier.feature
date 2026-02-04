Feature: Gestion du panier

  Scenario: Ajouter un article au panier avec succès
    Given l'utilisateur est connecté à l'application SauceDemo
    When il ajoute le produit "Sauce Labs Backpack" au panier
    Then le bouton du panier affiche "1" article