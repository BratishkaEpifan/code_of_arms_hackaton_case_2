//
//  MyView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 09.07.2022.
//

import UIKit

class MyView: UIView {
	
	var budgetButton: UIButton = {
		let button = UIButton(type: .system)
		button.backgroundColor = .red
//		BAButton(imageTitle: "persent",
//							  topText: "Ваши расходы",
//							  midText: "-123141 ₸",
//							  bottomText: "Ожидания: 130000 ₸")
		button.translatesAutoresizingMaskIntoConstraints = false
		button.addTarget(self, action: #selector(goToApp), for: .touchUpInside)
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
	
	let clearView = UIView()
		
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
		
		clearView.translatesAutoresizingMaskIntoConstraints = false
		clearView.backgroundColor = .systemPink
		addSubview(clearView)
		NSLayoutConstraint.activate([
			clearView.topAnchor.constraint(equalTo: cardView.bottomAnchor, constant: 20),
			clearView.leadingAnchor.constraint(equalTo: leadingAnchor, constant: 10),
			clearView.trailingAnchor.constraint(equalTo: trailingAnchor, constant: -10),
			clearView.heightAnchor.constraint(equalToConstant: 300)
		])
		
	}
}
