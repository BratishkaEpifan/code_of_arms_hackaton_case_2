//
//  MyBankScrollView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 08.07.2022.
//

import UIKit

class MyBankScrollView: UIScrollView {
	
	let view = UIView()
	
	var budgetButton: BAButton = {
		let button = BAButton(imageTitle: "persent",
							  topText: "Ваши расходы",
							  midText: "-123141 ₸",
							  bottomText: "Ожидания: 130000 ₸")
		button.translatesAutoresizingMaskIntoConstraints = false
		button.addTarget(MyBankScrollView.self, action: #selector(goToApp), for: .touchUpInside)
		return button
	}()
	
	@objc func goToApp() {
		print("Tapped app button!")
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
		
	override init(frame: CGRect) {
		super.init(frame: frame)
		translatesAutoresizingMaskIntoConstraints = false
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	

	
	func configure() {
		showsVerticalScrollIndicator = false
		view.translatesAutoresizingMaskIntoConstraints = false
		view.addSubview(budgetButton)
		NSLayoutConstraint.activate([
			budgetButton.topAnchor.constraint(equalTo: view.safeAreaLayoutGuide.topAnchor, constant: 10),
			budgetButton.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			budgetButton.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10),
			budgetButton.heightAnchor.constraint(equalToConstant: 100)
		])
		
		view.addSubview(bonusView)
		NSLayoutConstraint.activate([
			bonusView.topAnchor.constraint(equalTo: budgetButton.bottomAnchor, constant: 10),
			bonusView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			bonusView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10),
			bonusView.heightAnchor.constraint(equalToConstant: 100)
		])
		
		view.addSubview(sectionTitleLabel)
		NSLayoutConstraint.activate([
			sectionTitleLabel.topAnchor.constraint(equalTo: bonusView.bottomAnchor, constant: 20),
			sectionTitleLabel.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 20),
			sectionTitleLabel.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10),
			sectionTitleLabel.heightAnchor.constraint(equalToConstant: 40)
		])

		view.addSubview(cardView)
		NSLayoutConstraint.activate([
			cardView.topAnchor.constraint(equalTo: sectionTitleLabel.bottomAnchor, constant: 20),
			cardView.leadingAnchor.constraint(equalTo: view.leadingAnchor, constant: 10),
			cardView.trailingAnchor.constraint(equalTo: view.trailingAnchor, constant: -10),
			cardView.heightAnchor.constraint(equalToConstant: 100)
		])
		
		addSubview(view)
	}
}
