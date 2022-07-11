//
//  MyBankScrollView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

protocol GoToAppDelegate: AnyObject {
	func goToApp() -> Void
}

class MyBankView: UIView {
	
	weak var goToAppDelegate: GoToAppDelegate?
	
	let view = UIView()
	
	var budgetButton: BAButton = {
		let button = BAButton(imageTitle: "persent",
							  topText: "Ваши расходы",
							  midText: "-123141 ₸",
							  bottomText: "Ожидания: 130000 ₸")
		button.translatesAutoresizingMaskIntoConstraints = false
		button.addTarget(self, action: #selector(goToApp), for: .touchUpInside)
		return button
	}()
	
	@objc func goToApp() {
		goToAppDelegate?.goToApp()
	}
		  
	let bonusView = BAView(imageTitle: "bonus",
						   topText: "Бонусы 2 %",
						   midText: "3890 Б",
						   bottomText: "Максимальный уровень\n ....................................................................")
	
	let sectionTitleLabel = BALabel(text: "Карты")
	
	let cardView = BAView(imageTitle: "card",
						  topText: "Платежная карта •3333",
						  midText: "573890 ₸",
						  bottomText: "в блоке 750 ₸")
	
	let collectionViewImage = UIImageView(image: UIImage(named: "collection"))
		
	override init(frame: CGRect) {
		super.init(frame: frame)
		translatesAutoresizingMaskIntoConstraints = false
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	

	
	func configure() {
		translatesAutoresizingMaskIntoConstraints = false
		addSubview(budgetButton)
		NSLayoutConstraint.activate([
			budgetButton.topAnchor.constraint(equalTo: safeAreaLayoutGuide.topAnchor, constant: 10),
			budgetButton.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			budgetButton.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			budgetButton.heightAnchor.constraint(equalToConstant: 100)
		])
		
		addSubview(bonusView)
		NSLayoutConstraint.activate([
			bonusView.topAnchor.constraint(equalTo: budgetButton.bottomAnchor, constant: 10),
			bonusView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			bonusView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			bonusView.heightAnchor.constraint(equalToConstant: 100)
		])
		
		addSubview(sectionTitleLabel)
		NSLayoutConstraint.activate([
			sectionTitleLabel.topAnchor.constraint(equalTo: bonusView.bottomAnchor, constant: 20),
			sectionTitleLabel.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 20),
			sectionTitleLabel.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			sectionTitleLabel.heightAnchor.constraint(equalToConstant: 40)
		])

		addSubview(cardView)
		NSLayoutConstraint.activate([
			cardView.topAnchor.constraint(equalTo: sectionTitleLabel.bottomAnchor, constant: 20),
			cardView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			cardView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			cardView.heightAnchor.constraint(equalToConstant: 100)
		])
		
		collectionViewImage.translatesAutoresizingMaskIntoConstraints = false
		collectionViewImage.layer.masksToBounds = true
		collectionViewImage.layer.cornerRadius = 20
		addSubview(collectionViewImage)
		NSLayoutConstraint.activate([
			collectionViewImage.topAnchor.constraint(equalTo: cardView.bottomAnchor, constant: 20),
			collectionViewImage.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			collectionViewImage.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			collectionViewImage.heightAnchor.constraint(equalToConstant: 170)
		])

	}
}
