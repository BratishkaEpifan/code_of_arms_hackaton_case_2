//
//  BATopThirdView.swift
//  BudgetingApp
//
//  Created by Aidyn Assan on 13.07.2022.
//

import UIKit

class BATopThirdView: UIView {

	let topFirstLabel: UILabel = {
		let label = UILabel()
		label.text = "25000 ₸"
		label.textColor = .systemGreen
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let topSecondLabel: UILabel = {
		let label = UILabel()
		label.text = "35000 ₸"
		label.textColor = .systemRed
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	let centerLabel: UILabel = {
		let label = UILabel()
		label.text = "Лучшая комбинация бонусов"
		label.translatesAutoresizingMaskIntoConstraints = false
		return label
	}()
	
	override init(frame: CGRect) {
		super.init(frame: frame)
		backgroundColor = .white
		layer.masksToBounds = true
		layer.cornerRadius = 10
		translatesAutoresizingMaskIntoConstraints = false
		configure()
	}
	
	required init?(coder: NSCoder) {
		fatalError("init(coder:) has not been implemented")
	}
	
	func configure() {
		addSubview(centerLabel)
		addSubview(topFirstLabel)
		addSubview(topSecondLabel)
		NSLayoutConstraint.activate([
			centerLabel.centerXAnchor.constraint(equalTo: centerXAnchor),
			centerLabel.centerYAnchor.constraint(equalTo: centerYAnchor),
			
			topFirstLabel.leadingAnchor.constraint(equalTo: centerLabel.leadingAnchor),
			topFirstLabel.bottomAnchor.constraint(equalTo: centerLabel.topAnchor),
			
			topSecondLabel.trailingAnchor.constraint(equalTo: centerLabel.trailingAnchor),
			topSecondLabel.bottomAnchor.constraint(equalTo: centerLabel.topAnchor)
		])
	}
}
