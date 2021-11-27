.container
  form
    .row
      h4 Account
      .input-group.input-group-icon
        input(type="text", placeholder="Full Name")
        .input-icon
          i.fa.fa-user
      .input-group.input-group-icon
        input(type="email", placeholder="Email Adress")
        .input-icon
          i.fa.fa-envelope
      .input-group.input-group-icon
        input(type="password", placeholder="Password")
        .input-icon
          i.fa.fa-key
    .row
      .col-half
        h4 Date of Birth
        .input-group
          .col-third
            input(type="text", placeholder="DD")
          .col-third
            input(type="text", placeholder="MM")
          .col-third
            input(type="text", placeholder="YYYY")
      .col-half
        h4 Gender
        .input-group
          input#gender-male(type="radio", name="gender", value="male")
          label(for="gender-male") Male
          input#gender-female(type="radio", name="gender", value="female")
          label(for="gender-female") Female
    .row
      h4 Payment Details
      .input-group
        input#payment-method-card(
          type="radio",
          name="payment-method",
          value="card",
          checked="true"
        )
        label(for="payment-method-card")
          span
            i.fa.fa-cc-visa
            | Credit Card
        input#payment-method-paypal(
          type="radio",
          name="payment-method",
          value="paypal"
        )
        label(for="payment-method-paypal") 
          span
            i.fa.fa-cc-paypal
            | Paypal
      .input-group.input-group-icon
        input(type="text", placeholder="Card Number")
        .input-icon
          i.fa.fa-credit-card
      .col-half
        .input-group.input-group-icon
          input(type="text", placeholder="Card CVC")
          .input-icon
            i.fa.fa-user
      .col-half
        .input-group
          select
            option 01 Jan
            option 02 Jan
          select
            option 2015
            option 2016
    .row
      h4 Terms and Conditions
      .input-group
        input#terms(type="checkbox")
        label(for="terms") I accept the terms and conditions for signing up to this service, and hereby confirm I have read the privacy policy.
